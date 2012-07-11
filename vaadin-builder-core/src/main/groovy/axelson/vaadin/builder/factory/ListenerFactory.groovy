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

import java.lang.reflect.Method

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ListenerFactory extends FamilyFactory {
	private static final Logger logger = LoggerFactory.getLogger(ListenerFactory)
	
	ListenerFactory(Class klass) {
		super(klass)
	}
	
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		Class listenerType = getTargetType()
		assert listenerType.isInterface()
		assert listenerType.methods.size() == 1
		Method listenerMethod = listenerType.methods[0]
		assert listenerMethod.parameterTypes.size() == 1
		Class eventType = listenerMethod.parameterTypes[0]
		
		def listenerCode = """
		class script${System.currentTimeMillis()} implements ${listenerType.name}, axelson.vaadin.builder.factory.ListenerFactory.PluggableListener {
			Closure strategy

			void ${listenerMethod.name}(${eventType.name} e) {
				strategy.call(e)
			}
		}
		"""
		
		generateClass(listenerCode).newInstance()
	}

	@Override
	public boolean isHandlesNodeChildren() {
		return true
	}

	@Override
	public boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
		if (node instanceof PluggableListener) {
			node.strategy = childContent
		}
		return false
	}
	
	private Class generateClass(String code) {
		new GroovyClassLoader().parseClass(code)
	}
	
	static interface PluggableListener{}
}
