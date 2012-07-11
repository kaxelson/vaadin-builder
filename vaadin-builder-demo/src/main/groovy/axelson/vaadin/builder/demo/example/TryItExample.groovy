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
import com.vaadin.ui.Label
import com.vaadin.ui.Panel
import com.vaadin.ui.TextArea

class TryItExample implements ExampleProvider {
	@Override
	public String getName() {
		'Try It!'
	}

	@Override
	public String getCode() {
		''
	}

	@Override
	public String getDocumentation() {
		''
	}

	@Override
	public Component getComponent() {
		TextArea taSetup
		TextArea ta
		Panel p
		Button b
		new VaadinBuilder().verticalLayout(spacing: true, width: '100%') {
			taSetup = textArea(caption: 'Setup Code', width: '100%')
			ta = textArea(caption: 'Builder Code', width: '100%')
			b = button(caption: 'Render') {
				buttonClick {e ->
					if (ta.value != null) {
						p.removeAllComponents()
						try {
							def result = new GroovyShell().evaluate("${taSetup.value ?: ''} \n new axelson.vaadin.builder.VaadinBuilder().with {${ta.value}}")
							p.addComponent(result)
							b.setComponentError(null)
						} catch (Throwable t) {
							p.addComponent(new Label(t.message))
						}
					}
				}
			}
			p = panel(caption: 'Result', width: '100%') {
				label(value: 'Click Render to display your Vaadin UI')
			}
		}
	}
}
