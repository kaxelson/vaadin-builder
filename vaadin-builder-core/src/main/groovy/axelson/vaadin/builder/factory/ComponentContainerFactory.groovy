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

import com.vaadin.ui.Component
import com.vaadin.ui.ComponentContainer

@Slf4j
class ComponentContainerFactory extends ComponentFactory {
	ComponentContainerFactory(Class klass) {
		super(klass)
	}
	
	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof ComponentContainer) {
			children.each {child ->
				if (child instanceof Component) {
					node.addComponent(child)
				}
			}
		}
		super.processNodeChildren(builder, parent, node, children)
	}
}
