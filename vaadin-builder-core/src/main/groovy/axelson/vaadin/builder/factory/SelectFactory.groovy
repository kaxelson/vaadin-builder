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

import com.vaadin.data.Container
import com.vaadin.data.Item
import com.vaadin.data.util.IndexedContainer
import com.vaadin.ui.AbstractSelect

@Slf4j
class SelectFactory extends FieldFactory {
	SelectFactory(Class klass) {
		super(klass)
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		if (node instanceof AbstractSelect) {
			AbstractSelect select = node
			attributes.remove('options')?.with {options ->
				final Container c = new IndexedContainer();
				options.each {o ->
					c.addItem(o)
				}
				select.containerDataSource = c
			}
		}
		return super.onHandleNodeAttributes(builder, node, attributes)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof AbstractSelect) {
			AbstractSelect s = node
			children.findAll{it instanceof Item}.each {child ->
				if (child.hasProperty('itemId')) {
					s.addItem(child.itemId)
				} else {
					s.addItem(child)
				}
				children.remove(child)
			}
		}
		super.processNodeChildren(builder, parent, node, children)
	}
}
