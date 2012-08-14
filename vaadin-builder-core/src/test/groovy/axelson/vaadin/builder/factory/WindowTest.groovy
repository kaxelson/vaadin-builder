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

import spock.lang.Specification

import axelson.vaadin.builder.VaadinBuilder;

import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.Layout
import com.vaadin.ui.Window
import com.vaadin.ui.Layout.MarginInfo

class WindowTest extends Specification {
	def 'can create a new window'() {
		when:
			Window window = new VaadinBuilder().window()

		then:
			window && window instanceof Window
	}

	def 'can create a new window with attributes'() {
		when:
			Window window = new VaadinBuilder().window(caption: 'Test Window', positionX: 100, positionY: 100)

		then:
			window && window instanceof Window
			window.caption == 'Test Window'
			window.positionX == 100
			window.positionY == 100
	}

	def 'can add a component to a window'() {
		when:
			Window window = new VaadinBuilder().window {
				label(value: 'test')
			}

		then:
			window && window instanceof Window
			window.componentIterator.any {it instanceof Label && it.value == 'test'}
	}

	def 'can add a listener to a window'() {
		setup:
			def test
			Window window = new VaadinBuilder().window {
				windowClose {e ->
					test = 'worked'
				}
			}

		when:
			window.close()

		then:
			window.getListeners(Window.CloseEvent).size() == 1
			test == 'worked'
	}

	def 'can add a listener to a popup window'() {
		setup:
			Window w
			Button b
			List test = []
			new VaadinBuilder().window {
				Window pw = current
				b = button(caption: 'Click for new Window') {
					buttonClick {Button.ClickEvent event ->
						w = window(caption: 'Test Window', positionX: 100, positionY: 100) {
							windowClose {Window.CloseEvent e ->
								test << 'worked'
								pw.removeWindow(e.window)
							}
						}
						pw.addWindow(w)
					}
				}
			}

		when:
			b.click()
			w.close()

		then:
			w && w instanceof Window
			w.getListeners(Window.CloseEvent).size() == 1
			test[0] == 'worked'
	}

	def 'can set spacing and margin on a window'() {
		when:
			Window window = new VaadinBuilder().window(spacing: true, margin: true)

		then:
			window.content instanceof Layout.MarginHandler
			window.content instanceof Layout.SpacingHandler
			window.content.margin == new MarginInfo(true)
			window.content.spacing == true
	}

	def 'can set alignment on a window component'() {
		when:
			Label l1
			Label l2
			Window window = new VaadinBuilder().window(width: '500px') {
				l1 = label(alignment: Alignment.BOTTOM_LEFT, value: 'test')
				l2 = label(alignment: Alignment.TOP_RIGHT, value: 'test')
			}

		then:
			window.content instanceof Layout.AlignmentHandler
			window.content.getComponentAlignment(l1) == Alignment.BOTTOM_LEFT
			window.content.getComponentAlignment(l2) == Alignment.TOP_RIGHT
	}
}
