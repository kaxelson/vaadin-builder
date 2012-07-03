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

import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

class WindowExample implements ExampleProvider {
	@Override
	public Component getExample() {
	}

	@Override
	public String getCode() {
'''Button b = new Button('Click for new Window')
b.addListener([
	buttonClick: {Button.ClickEvent event ->
		Window w = new VaadinBuilder().window()
		vl.application.mainWindow.addWindow(w)
		w.addListener([
			windowClose: {Window.CloseEvent e ->
				vl.application.mainWindow.removeWindow(w)
			}
		] as Window.CloseListener)
	}
] as Button.ClickListener)'''
	}

	@Override
	public String getDocumentation() {
		''
	}

	@Override
	public Component getComponent() {
		VerticalLayout vl = new VerticalLayout()
		Button b = new Button('Click for new Window')
		b.addListener([
			buttonClick: {Button.ClickEvent event ->
				Window w = new VaadinBuilder().window()
				vl.application.mainWindow.addWindow(w)
				w.addListener([
					windowClose: {Window.CloseEvent e ->
						vl.application.mainWindow.removeWindow(w)
					}
				] as Window.CloseListener)
			}
		] as Button.ClickListener)
		vl.addComponent(b)
		return vl
	}
}
