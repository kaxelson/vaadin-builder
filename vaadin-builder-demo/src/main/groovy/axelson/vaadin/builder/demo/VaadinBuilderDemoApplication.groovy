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

package axelson.vaadin.builder.demo

import axelson.vaadin.builder.demo.example.WindowExample

import com.vaadin.Application
import com.vaadin.ui.Label
import com.vaadin.ui.TabSheet
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

class VaadinBuilderDemoApplication extends Application {
	private TabSheet ts
	
	@Override
	void init() {
		mainWindow = new Window()
		mainWindow.caption = 'VaadinBuilder Demo'
		ts = new TabSheet()
		mainWindow.addComponent(ts)
		addExample(new WindowExample())
	}
	
	private void addExample(ExampleProvider ep) {
		VerticalLayout vl = new VerticalLayout()
		vl.addComponent(new Label(ep.code))
		vl.addComponent(ep.component)
		ts.addTab(vl, ep.class.name)
	}
}

