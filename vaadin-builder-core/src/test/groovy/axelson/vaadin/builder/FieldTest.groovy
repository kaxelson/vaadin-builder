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
import com.vaadin.ui.CheckBox
import com.vaadin.ui.DateField
import com.vaadin.ui.RichTextArea
import com.vaadin.ui.TextField

class FieldTest extends Specification {
	def 'can create a new Button'() {
		when:
			Button b = new VaadinBuilder().button(caption: 'Test Button')
			
		then:
			b && b instanceof Button
			b.caption == 'Test Button'
	}

	def 'can create a new TextField'() {
		when:
			TextField f = new VaadinBuilder().textField(caption: 'Test Text Field')
			
		then:
			f && f instanceof TextField
			f.caption == 'Test Text Field'
	}
	
	def 'can create a new DateField'() {
		when:
			DateField f = new VaadinBuilder().dateField(caption: 'Test Date Field')
			
		then:
			f && f instanceof DateField
			f.caption == 'Test Date Field'
	}

	def 'can create a new CheckBox'() {
		when:
			CheckBox f = new VaadinBuilder().checkBox(caption: 'Test CheckBox')
			
		then:
			f && f instanceof CheckBox
			f.caption == 'Test CheckBox'
	}

	def 'can create a new RichTextArea'() {
		when:
			RichTextArea f = new VaadinBuilder().richTextArea(caption: 'Test RichTextArea')
			
		then:
			f && f instanceof RichTextArea
			f.caption == 'Test RichTextArea'
	}
}
