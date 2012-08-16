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

import groovy.util.logging.Slf4j
import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.Application
import com.vaadin.terminal.ClassResource
import com.vaadin.ui.Button
import com.vaadin.ui.Panel
import com.vaadin.ui.TextArea
import com.vaadin.ui.UriFragmentUtility
import com.vaadin.ui.Window
import com.vaadin.ui.themes.BaseTheme

@Slf4j
class VaadinBuilderDemoApplication extends Application {
	static final long serialVersionUID = 1L

	Map samples = [
		'tooltips': 'Tooltips',
		'icons': 'Icons',
		'packageIcons': 'Runo theme icons',
		'errors': 'Error indicator',
		'progressIndicators': 'Progress indication',
		'jsapi': 'JavaScript API',
		'browserInformation': 'Browser information',
		'buttonPush': 'Push button',
		'buttonDisableOnClick': 'Disable button on click',
		'buttonLink': 'Link button',
		'checkBox': 'Checkbox',
		'linkCurrentWindow': 'Link',
		'linkNoDecorations': 'Link, configure window',
		'linkSizedWindow': 'Link, sized window',
		'labelPlain': 'Label, plain text',
		'labelPreformatted': 'Label, preformatted',
		'labelRich': 'Label, rich text',
		'imageEmbed': 'Image',
		'flashEmbed': 'Flash',
		'webEmbed': 'Web content',
		'shortcutBasics': 'Shortcuts, basics',
		'shortcutScope': 'Shortcuts, scope',
	]

	TextArea ta
	Panel p
	Button b

	@Override
	void init() {
		theme = 'vbd'
		def contoller = this
		mainWindow = new VaadinBuilder().window(caption: 'VaadinBuilder Demo') {
			UriFragmentUtility urifu = uriFragmentUtility {
				fragmentChanged {UriFragmentUtility.FragmentChangedEvent e ->
					contoller.loadSample(e.uriFragmentUtility.fragment)
				}
			}
			horizontalLayout(width: '100%', spacing: true) {
				verticalLayout(spacing: true, width: '150px') {
					this.samples.each {k, v ->
						button(caption: v, styleName: BaseTheme.BUTTON_LINK) {
							buttonClick {e ->
								urifu.fragment = k
							}
						}
					}
				}
				verticalLayout(spacing: true, expandRatio: 1) {
					ta = textArea(caption: 'Code', width: '100%', rows: 20, wordwrap: false, value: 'Type you builder code here or click one of the links for an example.')
					b = button(caption: 'Render') {
						buttonClick {
							contoller.renderBuilderCode()
						}
					}
					p = panel(caption: 'Result', width: '100%') {
						label(value: 'Click Render to display your Vaadin UI')
					}
				}
			}
		}
	}

	private void loadSample(String sample) {
		if (sample) {
			ClassResource cr = new ClassResource("/vaadin/${sample}.groovy", this)
			String sampleCode = cr.stream.stream.text
			ta.value = sampleCode
			renderBuilderCode()
		}
	}

	private void renderBuilderCode() {
		if (ta.value) {
			p.removeAllComponents()
			try {
				//Need this binding so that properties that are meant to be set
				//on the builder are not inadvertently set on the binding.
				Binding binding = new Binding() {
					Object getVariable(String name) {
						throw new MissingPropertyException(name, getClass())
					}

					void setVariable(String name, Object value) {
						throw new MissingPropertyException(name, getClass())
					}
				}

				def result = new GroovyShell(binding).evaluate(ta.value)
				p.addComponent(result)
				b.componentError = null
			} catch (Throwable t) {
				String stackTrace = new StringWriter().withWriter {w ->
					t.printStackTrace(new PrintWriter(w))
					w.toString()
				}
				p.addComponent(new VaadinBuilder().textArea(value: stackTrace, width: '100%', rows: 20))
			}
		}
	}
}
