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

import com.vaadin.terminal.Sizeable
import com.vaadin.ui.TextArea
import com.vaadin.ui.VerticalLayout

class ComponentTest extends Specification {
	def 'can set width on a layout'() {
		when:
			VerticalLayout vl = new VaadinBuilder().verticalLayout(width: '100%')

		then:
			vl && vl instanceof Sizeable
			vl.width == 100
			vl.widthUnits == Sizeable.UNITS_PERCENTAGE
	}

	def 'can set width on a field'() {
		when:
			TextArea ta = new VaadinBuilder().textArea(width: '100%')

		then:
			ta && ta instanceof Sizeable
			ta.width == 100
			ta.widthUnits == Sizeable.UNITS_PERCENTAGE
	}

	def 'current refers to the enclosing node'() {
		when:
			def test
			VerticalLayout vl = new VaadinBuilder().verticalLayout {
				test = current
			}

		then:
			test == vl
	}
}
