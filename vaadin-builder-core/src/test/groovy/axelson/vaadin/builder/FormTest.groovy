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

import com.vaadin.data.util.BeanItem
import com.vaadin.ui.Form

class FormTest extends Specification {
	def 'can create a Form'() {
		when:
			Form f = new VaadinBuilder().form()
			
		then:
			f && f instanceof Form
	}
	
	def 'can create a Form with an itemDataSource'() {
		setup:
		when:
			Form f = new VaadinBuilder().form(itemDataSource: new BeanItem(new A()))
			
		then:
			f && f instanceof Form
			f.getField('a') != null
			f.getField('b') != null
	}
	
	static class A {
		int a, b
	}
}
