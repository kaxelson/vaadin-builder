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

package axelson.vaadin.builder

import spock.lang.Specification

import com.vaadin.Application
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.ModifierKey
import com.vaadin.ui.Button
import com.vaadin.ui.Panel
import com.vaadin.ui.TextField
import com.vaadin.ui.Window
import com.vaadin.ui.AbstractField.FocusShortcut

class ExplicitMethodTest extends Specification {
	def 'can add a listener with listener method'() {
		setup:
			String test = null
			def clickListener = [
				buttonClick: {Button.ClickEvent e ->
					test = 'clicked'
				}
			] as Button.ClickListener
			Button b = new VaadinBuilder().button {
				//adding the listener with the listener explicit method
				listener(clickListener)
			}

		when:
			b.click()

		then:
			b.getListeners(Button.ClickEvent).size() == 1
			test == 'clicked'
	}

	def 'can add a shortcut listener with shortcutListener method'() {
		when:
			def sl
			TextField tf = new VaadinBuilder().textField(caption: 'Firstname', inputPrompt: 'ALT-SHIFT-F to focus') {
				//adding the listener with the listener explicit method
				sl = new FocusShortcut(current, KeyCode.F, ModifierKey.ALT, ModifierKey.SHIFT)
				shortcutListener(sl)
			}

		then:
			tf.actionManager.getActions(null, null).any {it == sl}
			tf.actionManager.getActions(null, null).find {it == sl}.focusable == tf
	}

	def 'can add an action with action method'() {
		when:
			def a
			def tf
			Panel p = new VaadinBuilder().panel {
				tf = textField(caption: 'Firstname', inputPrompt: 'ALT-SHIFT-F to focus')
				a = new FocusShortcut(tf, KeyCode.F, ModifierKey.ALT, ModifierKey.SHIFT)
				//adding the listener with the listener explicit method
				action(a)
			}

		then:
			p.actionManager.getActions(null, null).any {it == a}
			p.actionManager.getActions(null, null).find {it == a}.focusable == tf
	}

	def 'can set attach logic with attach method'() {
		setup:
			Button b
			Window w = new VaadinBuilder().window {
				b = button(caption: 'before') {
					attach {
						it.caption = 'after'
					}
				}
			}
			Application a = new Application() {
				public void init() {
					mainWindow = w
				}
			}

		when:
			a.init()

		then:
			b.caption == 'after'
	}
}
