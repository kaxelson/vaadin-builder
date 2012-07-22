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

package axelson.vaadin.builder.demo.example

import axelson.vaadin.builder.VaadinBuilder
import axelson.vaadin.builder.demo.ExampleProvider

import com.vaadin.ui.Component

class ButtonExample implements ExampleProvider {
	@Override
	public String getName() {
		'Button'
	}

	@Override
	public String getCode() {
		'''button(caption: 'Clicking this button will do nothing')'''
	}

	@Override
	public String getDocumentation() {
		''
	}

	@Override
	public Component getComponent() {
		new VaadinBuilder().button(caption: 'Clicking this button will do nothing')
	}
}
