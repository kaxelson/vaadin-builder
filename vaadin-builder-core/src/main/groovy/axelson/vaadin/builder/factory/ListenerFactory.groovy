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

import groovy.lang.Closure;
import groovy.util.FactoryBuilderSupport;

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent;

class ListenerFactory extends FamilyFactory {
	private static final Logger logger = LoggerFactory.getLogger(ListenerFactory)
	
	ListenerFactory(Class klass) {
		super(klass)
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
}

class PluggableListener {
	Closure strategy
}

class PluggableButtonClickListener extends PluggableListener implements Button.ClickListener {
	@Override
	public void buttonClick(ClickEvent event) {
		strategy.call(event)
	}
}
