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

import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.TabSheet
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.TabSheet.Tab

class TabSheetTest extends Specification {
	def 'can create a TabSheet'() {
		when:
			TabSheet t = new VaadinBuilder().tabSheet()
			
		then:
			t && t instanceof TabSheet
	}
	
	def 'can add a tab to a TabSheet'() {
		when:
			TabSheet t = new VaadinBuilder().tabSheet() {
				tab(caption: 'test')
			}
			
		then:
			t && t instanceof TabSheet
			def tab = t.getTab(0)
			tab
			tab.caption == 'test'
			tab.component instanceof VerticalLayout
	}
	
	def 'can add a tab with a component to a TabSheet'() {
		when:
			TabSheet t = new VaadinBuilder().tabSheet() {
				tab(caption: 'test') {
					horizontalLayout {
						label(value: 'test')
					}
				}
			}
			
		then:
			t && t instanceof TabSheet
			Tab tab = t.getTab(0)
			tab
			tab.caption == 'test'
			tab.component && tab.component instanceof HorizontalLayout
			HorizontalLayout hl = tab.component
			Label label = hl.components.find {it instanceof Label}
			label
			label.value == 'test'
	}
}
