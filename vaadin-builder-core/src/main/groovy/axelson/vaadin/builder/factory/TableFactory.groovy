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
import axelson.vaadin.builder.factory.ColumnFactory.Column
import axelson.vaadin.builder.factory.ColumnFactory.PluggableColumnGenerator
import axelson.vaadin.builder.util.NewBuilderClosure

import com.vaadin.data.Item
import com.vaadin.terminal.Resource
import com.vaadin.ui.Table
import com.vaadin.ui.Table.ColumnGenerator

@Slf4j
class TableFactory extends SelectFactory {
	TableFactory() {
		super(Table)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		if (node instanceof Table) {
			Table table = node
			def propertyIds = table.containerPropertyIds

			children.findAll{it instanceof Column}.each {child ->
				Column column = child

				if (!propertyIds.contains(column.propertyId)) {
					if (column.generator) {
						table.addGeneratedColumn(column.propertyId, column.generator)
					} else if (column.propertyId instanceof String && column.propertyId.indexOf('.') > 0) {
						table.getContainerDataSource().addNestedContainerProperty(column.propertyId)
					} else {
						try {
							table.addContainerProperty(column.propertyId, column.type, column.defaultValue)
						} catch (UnsupportedOperationException e) {
							table.addGeneratedColumn(column.propertyId, new PluggableColumnGenerator())
						}
					}
				}

				// columnCollapsingAllowed must be true in order to collapse a column
				if (table.columnCollapsingAllowed) {
					table.setColumnCollapsed(column.propertyId, column.collapsed)
					table.setColumnCollapsible(column.propertyId, column.collapsible)
				}

				if (column.alignment != null) {table.setColumnAlignment(column.propertyId, column.alignment)}
				if (column.expandRatio > 0) {table.setColumnExpandRatio(column.propertyId, column.expandRatio)}
				if (column.footer != null) {table.setColumnFooter(column.propertyId, column.footer)}
				if (column.header != null) {table.setColumnHeader(column.propertyId, column.header)}
				if (column.icon != null) {table.setColumnIcon(column.propertyId, column.icon)}
				if (column.width > 0) {table.setColumnWidth(column.propertyId, column.width)}
				children.remove(child)
			}

			children.findAll{it instanceof Item}.each {child ->
				List values = []
				table.visibleColumns.each {columnId ->
					if (!table.getColumnGenerator(columnId)) {
						values << child.getItemProperty(columnId)?.value
					}
				}
				table.addItem(values as Object[], child.itemId)
				children.remove(child)
			}
		}
		super.processNodeChildren(builder, parent, node, children)
	}
}

@Slf4j
class ColumnFactory extends AbstractFactory {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		new Column()
	}

	@Override
	public boolean isHandlesNodeChildren() {
		return true
	}

	@Override
	public boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
		if (node instanceof Column) {
			Column column = node
			//need to wrap the closure to make sure it serializes correctly
			column.generator = new PluggableColumnGenerator(strategy: new NewBuilderClosure(childContent))
		}
		return false
	}

	@Override
	public void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
		assert node instanceof Column && node.propertyId
		super.onNodeCompleted(builder, parent, node)
	}

	static class Column {
		Object propertyId
		String alignment
		String header
		String footer
		Resource icon
		float expandRatio
		int width
		ColumnGenerator generator
		boolean collapsed = false
		boolean collapsible = false
		Class type = String
		Object defaultValue = ''
	}

	static class PluggableColumnGenerator implements ColumnGenerator {
		Closure strategy = new NewBuilderClosure({def s, i, c ->})

		@Override
		public Object generateCell(Table source, Object itemId, Object columnId) {
			strategy.call(source, itemId, columnId)
		}
	}
}