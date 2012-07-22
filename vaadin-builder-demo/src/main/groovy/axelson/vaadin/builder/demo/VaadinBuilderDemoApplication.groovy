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

import axelson.vaadin.builder.VaadinBuilder
import axelson.vaadin.builder.demo.example.ButtonExample
import axelson.vaadin.builder.demo.example.TryItExample
import axelson.vaadin.builder.demo.example.WindowExample

import com.vaadin.Application
import com.vaadin.ui.Window

class VaadinBuilderDemoApplication extends Application {
	static final long serialVersionUID = 1L
	
	def examples = [
		new TryItExample(),
		new ButtonExample(),
		new WindowExample()
	]
	
	@Override
	void init() {
		mainWindow = new VaadinBuilder().window(caption: 'VaadinBuilder Demo') {
			tabSheet {
				this.examples.each {example ->
					tab(caption: example.name) {
						verticalLayout(margin: true, spacing: true) {
							label(value: example.code)
							component(example.component)
						}
					}
				}
			}
		}
	}
}
