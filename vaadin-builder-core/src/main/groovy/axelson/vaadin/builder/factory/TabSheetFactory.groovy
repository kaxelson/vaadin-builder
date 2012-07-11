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

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import axelson.vaadin.builder.factory.TabFactory.Tab

import com.vaadin.terminal.Resource
import com.vaadin.ui.Component
import com.vaadin.ui.TabSheet
import com.vaadin.ui.VerticalLayout

class TabSheetFactory extends ComponentContainerFactory {
	private static final Logger logger = LoggerFactory.getLogger(TabSheetFactory)
	
	TabSheetFactory(Class klass) {
		super(klass)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof TabSheet) {
			TabSheet tabSheet = node
			
			children.findAll{it instanceof Tab}.each {child ->
				Tab tab = child
				assert tab.component
				tabSheet.addTab(tab.component, tab.caption, tab.icon)
			}
		}
		super.processNodeChildren(builder, parent, node, children)
	}
}

class TabFactory extends AbstractFactory {
	private static final Logger logger = LoggerFactory.getLogger(TabFactory)

	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		new Tab()
	}
	
	@Override
	public void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
		if (parent instanceof Tab && child instanceof Component) {
			Tab tab = parent
			Component component = child
			tab.component = component
		}
		super.setChild(builder, parent, child)
	}
	
	static class Tab {
		String caption
		Resource icon
		Component component = new VerticalLayout()
		
		String getCaption() {
			caption ?: component.caption
		}
		
		Resource getIcon() {
			icon ?: component.icon
		}
	}
}