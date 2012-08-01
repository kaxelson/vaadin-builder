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

import com.vaadin.ui.MenuBar

class MenuTest extends Specification {
	def 'can create an empty MenuBar'() {
		when:
			MenuBar mb = new VaadinBuilder().menuBar()

		then:
			mb && mb instanceof MenuBar
			mb.size == 0
	}

	def 'can create a MenuBar with top-level MenuItems'() {
		when:
			MenuBar mb = new VaadinBuilder().menuBar {
				menuItem(caption: 'test1')
				menuItem(caption: 'test2')
			}

		then:
			mb && mb instanceof MenuBar
			mb.size == 2
			mb.items*.text == ['test1', 'test2']
	}

	def 'can create a multi-level MenuBar'() {
		when:
			MenuBar mb = new VaadinBuilder().menuBar {
				menuItem(caption: 'test1') {
					menuItem(caption: 'test1-1')
				}
				menuItem(caption: 'test2')
			}

		then:
			mb && mb instanceof MenuBar
			mb.size == 2
			mb.items*.text == ['test1', 'test2']
			mb.items.find{it.text == 'test1'} != null
			mb.items.find{it.text == 'test1'} instanceof MenuBar.MenuItem
			mb.items.find{it.text == 'test1'}.hasChildren()
			mb.items.find{it.text == 'test1'}.children.size == 1
			mb.items.find{it.text == 'test1'}.children*.text == ['test1-1']
	}

	def 'can create a multi-level MenuBar with a separator'() {
		when:
			MenuBar mb = new VaadinBuilder().menuBar {
				menuItem(caption: 'test1') {
					menuItem(caption: 'test1-1')
					menuSeparator()
					menuItem(caption: 'test1-2')
				}
				menuItem(caption: 'test2')
			}

		then:
			mb && mb instanceof MenuBar
			mb.size == 2
			mb.items*.text == ['test1', 'test2']
			mb.items.find{it.text == 'test1'} != null
			mb.items.find{it.text == 'test1'} instanceof MenuBar.MenuItem
			mb.items.find{it.text == 'test1'}.hasChildren()
			mb.items.find{it.text == 'test1'}.children.size == 3
			mb.items.find{it.text == 'test1'}.children*.text == ['test1-1', '', 'test1-2']
			mb.items.find{it.text == 'test1'}.children.findAll{it.separator}.size() == 1
	}
}
