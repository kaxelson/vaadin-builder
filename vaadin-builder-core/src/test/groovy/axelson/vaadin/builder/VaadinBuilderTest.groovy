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

import com.vaadin.ui.Button
import com.vaadin.ui.Label

class VaadinBuilderTest extends Specification {
	def "can set current node properties within that node's closure"() {
		when:
			Label l
			Button b
			new VaadinBuilder().verticalLayout {
				l = label {
					value = 'test'
				}
				b = button {
					caption = 'test'
				}
			}

		then:
			l.value == 'test'
			b.caption == 'test'
	}
}
