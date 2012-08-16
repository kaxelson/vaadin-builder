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
import com.vaadin.data.Property

@Slf4j
class ItemFactory extends AbstractFactory {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		new GenericItem()
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		node.itemId = attributes.remove('itemId')
		attributes.each {k, v ->
			node.addItemProperty(k, new GenericProperty(propertyId: k, value: v))
		}
		attributes.clear()
		return false
	}

	@Override
	public boolean isLeaf() {
		return true
	}

	static class GenericItem implements Item {
		Object itemId
		Map itemProperties = [:]
		@Override
		public Property getItemProperty(Object id) {
			itemProperties[id]
		}

		@Override
		public Collection<?> getItemPropertyIds() {
			itemProperties.keySet()
		}

		@Override
		public boolean addItemProperty(Object id, Property property) throws UnsupportedOperationException {
			if (!itemProperties[id]) {
				itemProperties[id] = property
				return true
			}
			return false
		}

		@Override
		public boolean removeItemProperty(Object id) throws UnsupportedOperationException {
			itemProperties.remove(id) != null
		}
	}

	static class GenericProperty implements Property {
		Object propertyId
		Object value
		boolean readOnly

		@Override
		public Class<?> getType() {
			return value.class
		}
	}
}
