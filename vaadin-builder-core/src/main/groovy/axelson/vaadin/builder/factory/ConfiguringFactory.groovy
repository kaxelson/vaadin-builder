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

@Slf4j
abstract class ConfiguringFactory extends AbstractFactory implements Serializable {
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		if (value && getTargetType()?.isAssignableFrom(value.class)) {
			return value
		}
		return doNewInstance(builder, name, value, attributes)
	}
	
	abstract Class getTargetType()
	
	Object doNewInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		getTargetType().newInstance()
	}
}
