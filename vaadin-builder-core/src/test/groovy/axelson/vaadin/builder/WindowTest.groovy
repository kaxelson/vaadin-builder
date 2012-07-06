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

import com.vaadin.ui.Window

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
}
