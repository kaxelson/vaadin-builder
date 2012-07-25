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
import com.vaadin.ui.Layout
import com.vaadin.ui.TextArea
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Layout.MarginHandler

class LayoutTest extends Specification {
	def 'can create a new verticalLayout'() {
		when:
			VerticalLayout vl = new VaadinBuilder().verticalLayout()
			
		then:
			vl && vl instanceof VerticalLayout
	}
	
	def 'can set expand ratio'() {
		when:
			TextArea ta1
			TextArea ta2
			HorizontalLayout hl = new VaadinBuilder().horizontalLayout(width: '100%') {
				ta1 = textArea(expandRatio: 1)
				ta2 = textArea(expandRatio: 2)
			}
			
		then:
			hl && hl instanceof Layout
			ta1 && ta1 instanceof TextArea
			ta2 && ta2 instanceof TextArea
			hl.getExpandRatio(ta1) == 1
			hl.getExpandRatio(ta2) == 2
	}
	
	def 'can set margin on layout'() {
		when:
			VerticalLayout vl = new VaadinBuilder().verticalLayout(margin: true)
			
		then:
			vl && vl instanceof Layout
			vl instanceof Layout.MarginHandler
			vl.margin && vl.margin instanceof Layout.MarginInfo
			vl.margin.hasBottom() && vl.margin.hasLeft() && vl.margin.hasRight() && vl.margin.hasTop()
	}
}
