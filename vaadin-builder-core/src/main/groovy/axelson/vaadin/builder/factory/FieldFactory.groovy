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

import com.vaadin.data.Property
import com.vaadin.data.Property.ConversionException
import com.vaadin.data.Property.ReadOnlyException
import com.vaadin.data.util.AbstractProperty
import com.vaadin.ui.Field

@Slf4j
class FieldFactory extends ComponentFactory {
	FieldFactory(Class klass) {
		super(klass)
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		if (node instanceof Field) {
			Field field = node
			attributes.remove('formPropertyId')?.with {formPropertyId ->
				field.propertyDataSource = new FormFieldPropertyWrapper(formPropertyId: formPropertyId)
			}
		}
		return super.onHandleNodeAttributes(builder, node, attributes);
	}

	static class FormFieldPropertyWrapper extends AbstractProperty implements Property.Viewer {
		Object formPropertyId
		Property propertyDataSource

		@Override
		public Object getValue() {
			propertyDataSource?.value
		}

		@Override
		public void setValue(Object newValue) throws ReadOnlyException, ConversionException {
			propertyDataSource?.value = newValue
		}

		@Override
		public Class<?> getType() {
			propertyDataSource?.type
		}
	}
}
