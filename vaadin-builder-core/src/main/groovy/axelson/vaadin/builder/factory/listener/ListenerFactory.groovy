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

package axelson.vaadin.builder.factory.listener

import groovy.util.logging.Slf4j
import axelson.vaadin.builder.factory.FamilyFactory
import axelson.vaadin.builder.util.NewBuilderClosure

@Slf4j
class ListenerFactory extends FamilyFactory {
	ListenerFactory(Class klass) {
		super(klass)
	}

	@Override
	public boolean isHandlesNodeChildren() {
		return true
	}

	@Override
	public boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
		if (node instanceof Pluggable) {
			Pluggable listener = node
			//need to wrap the closure to make sure it serializes correctly
			listener.strategy = new NewBuilderClosure(childContent)
		}
		return false
	}
}
