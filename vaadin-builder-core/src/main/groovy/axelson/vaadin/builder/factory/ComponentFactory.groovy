/*
 * Copyright 2012 Knute G. Axelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package axelson.vaadin.builder.factory

import groovy.util.logging.Slf4j
import axelson.vaadin.builder.factory.listener.Pluggable

import com.vaadin.terminal.Sizeable
import com.vaadin.ui.AbstractOrderedLayout
import com.vaadin.ui.Alignment
import com.vaadin.ui.Component
import com.vaadin.ui.Layout

@Slf4j
class ComponentFactory extends ChildDeferringFactory {
	ComponentFactory(Class klass) {
		super(klass)
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		attributes.remove('listener')?.with {listener ->
			addListener(node, listener)
		}
		attributes.remove('listeners')?.with {listeners ->
			assert listeners instanceof Iterable
			listeners.each {listener ->
				addListener(node, listener)
			}
		}
		attributes.remove('width')?.with {width ->
			if (node instanceof Sizeable) {
				if (width instanceof String) {
					node.setWidth(width as String)
				} else if (width instanceof Number) {
					node.setWidth(width as float)
				} else {
					log.warn 'width ignored, type must be String or Number'
				}
			} else {
				log.warn 'width ignored, node must be a Sizable'
			}
		}
		attributes.remove('height')?.with {height ->
			if (node instanceof Sizeable) {
				if (height instanceof String) {
					node.setHeight(height as String)
				} else if (height instanceof Number) {
					node.setHeight(height as float)
				} else {
					log.warn 'height ignored, type must be String or Number'
				}
			} else {
				log.warn 'height ignored, node must be a Sizable'
			}
		}
		attributes.remove('expandRatio')?.with {expandRatio ->
			// at this point builder.current points to the parent of this node
			if (builder.parentFactory) {
				builder.parentFactory.addNodeChild(builder.current, new ExpandRatioAttribute(component: node, expandRatio: expandRatio as float))
			} else {
				log.warn 'expandRatio ignored, no parent'
			}
		}
		attributes.remove('alignment')?.with {alignment ->
			// at this point builder.current points to the parent of this node
			if (builder.parentFactory) {
				builder.parentFactory.addNodeChild(builder.current, new AlignmentAttribute(component: node, alignment: alignment))
			} else {
				log.warn 'alignment ignored, no parent'
			}
		}
		super.onHandleNodeAttributes(builder, node, attributes)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof Component) {
			children.each {child ->
				if (child instanceof Pluggable) {
					log.info 'adding Pluggable[{}] to Component[{}]', child, node
					node.addListener(child)
				}
			}
		}
	}

	protected void addListener(Object node, Object listener) {
		if (node.respondsTo('addListener', [listener] as Object[])) {
			node.addListener(listener)
		} else {
			log.warn 'listener ignored, node does not support this listener'
		}
	}

	static class ExpandRatioAttribute {
		Component component
		float expandRatio
	}

	static class AlignmentAttribute {
		Component component
		Alignment alignment
	}
}
