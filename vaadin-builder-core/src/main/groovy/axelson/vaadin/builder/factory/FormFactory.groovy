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

import com.vaadin.data.Item
import com.vaadin.ui.Component
import com.vaadin.ui.ComponentContainer
import com.vaadin.ui.Field
import com.vaadin.ui.Form
import com.vaadin.ui.FormFieldFactory

@Slf4j
class FormFactory extends ChildDeferringFactory {
	FormFactory() {
		super(Form)
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		super.processNodeChildren(builder, parent, node, children)
		if (node instanceof Form) {
			Form form = node
			getFormFieldsRecursive(children).with {fields ->
				if (fields.size() > 0) {
					form.formFieldFactory = new CustomFormFieldFactory(fields: fields)
				}
			}
		}
	}

	@Override
	public void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
		super.onNodeCompleted(builder, parent, node)
		if (node instanceof Form) {
			Form form = node
			if (form.itemDataSource) {
				form.itemDataSource = form.itemDataSource
			}
		}
	}

	private List getFormFieldsRecursive(List nodes) {
		List fields = []
		nodes.each {node ->
			if (node instanceof Field) {
				Field field = node
				if (field.propertyDataSource && field.propertyDataSource instanceof FieldFactory.FormFieldPropertyWrapper) {
					fields << field
				}
			} else if (node instanceof ComponentContainer) {
				ComponentContainer cc = node
				fields.addAll(getFormFieldsRecursive(cc.componentIterator.toList()))
			}
		}
		return fields
	}

	static class CustomFormFieldFactory implements FormFieldFactory {
		List fields

		public Field createField(Item item, Object propertyId, Component uiContext) {
			fields.find {it.propertyDataSource.formPropertyId == propertyId}
		}
	}
}
