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

import com.vaadin.ui.Button
import com.vaadin.ui.CheckBox
import com.vaadin.ui.DateField
import com.vaadin.ui.InlineDateField
import com.vaadin.ui.NativeButton
import com.vaadin.ui.PasswordField
import com.vaadin.ui.PopupDateField
import com.vaadin.ui.ProgressIndicator
import com.vaadin.ui.RichTextArea
import com.vaadin.ui.Slider
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField

class FieldTest extends Specification {
	def 'can create a new Field'(Class type) {
		when:
			def node = toCamelCase(type.simpleName)
			def caption = "Test ${node}"
			def f = new VaadinBuilder()."${node}"(caption: caption)
			
		then:
			f && f.class == type
			f.caption == caption
		
		where:
			type << [Button, NativeButton, CheckBox, DateField, PopupDateField, InlineDateField,
				RichTextArea, TextField, TextArea, PasswordField, ProgressIndicator, Slider]
	}
	
	private String toCamelCase(String s) {
		if (s == null) throw new IllegalArgumentException('Cannot convert null String to camel case.')
		if (s.size() == 0) return s
		s[0].toLowerCase() + ((s.size() > 1) ? s[1..-1] : '')
	}
}
