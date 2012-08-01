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
import axelson.vaadin.builder.factory.listener.PluggableListener
import axelson.vaadin.builder.util.NewBuilderClosure

import com.vaadin.terminal.Resource
import com.vaadin.ui.MenuBar

@Slf4j
class MenuBarFactory extends ComponentFactory {
	MenuBarFactory() {
		super(MenuBar)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof MenuBar) {
			// build the entire menu structure here
			MenuBar menuBar = node
			children.each {child ->
				processMenuComponent(menuBar, child)
			}
		}
	}

	protected void processMenuComponent(def parent, def child) {
		if (child instanceof MenuItemFactory.MenuItem) {
			MenuItemFactory.MenuItem factoryMenuItem = child
			MenuBar.MenuItem menuItem = null

			if (parent instanceof MenuBar) {
				MenuBar menuBar = parent
				menuItem = menuBar.addItem(factoryMenuItem.caption, factoryMenuItem.icon, factoryMenuItem.command)
			}

			if (parent instanceof MenuBar.MenuItem) {
				MenuBar.MenuItem parentItem = parent
				menuItem = parentItem.addItem(factoryMenuItem.caption, factoryMenuItem.icon, factoryMenuItem.command)
				menuItem.checkable = factoryMenuItem.checkable
				menuItem.checked = factoryMenuItem.checkable
				menuItem.description = factoryMenuItem.checkable
				menuItem.enabled = factoryMenuItem.checkable
				menuItem.styleName = factoryMenuItem.checkable
				menuItem.visible = factoryMenuItem.checkable
			}

			if (menuItem) {
				factoryMenuItem.children.each {
					processMenuComponent(menuItem, it)
				}
			}
		}

		if (child instanceof MenuSeparatorFactory.MenuSeparator) {
			if (parent instanceof MenuBar.MenuItem) {
				MenuBar.MenuItem menuItem = parent
				menuItem.addSeparator()
			}
		}
	}
}

@Slf4j
class MenuItemFactory extends AbstractFactory {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		return new MenuItem()
	}

	@Override
	public void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
		super.setChild(builder, parent, child)
		if (parent instanceof MenuItem) {
			MenuItem menuItem = parent
			if (child instanceof MenuItem || child instanceof MenuSeparatorFactory.MenuSeparator) {
				menuItem.children << child
			}
			if (child instanceof MenuBar.Command) {
				menuItem.command = child
			}
		}
	}

	static class MenuItem {
		List children = []
		String caption
		Resource icon
		MenuBar.Command command
		boolean checkable
		boolean checked
		String description
		boolean enabled
		String styleName
		boolean visible
	}
}

@Slf4j
class MenuSeparatorFactory extends AbstractFactory {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		return new MenuSeparator()
	}

	@Override
	public boolean isLeaf() {
		return true
	}

	static class MenuSeparator {}
}


@Slf4j
class MenuCommandFactory extends AbstractFactory {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		return new PluggableMenuCommand()
	}

	@Override
	public boolean isHandlesNodeChildren() {
		return true
	}

	@Override
	public boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
		if (node instanceof PluggableMenuCommand) {
			PluggableMenuCommand command = node
			//need to wrap the closure to make sure it serializes correctly
			command.strategy = new NewBuilderClosure(childContent)
		}
		return false
	}

	@PluggableListener(MenuBar.Command) static class PluggableMenuCommand {}
}
