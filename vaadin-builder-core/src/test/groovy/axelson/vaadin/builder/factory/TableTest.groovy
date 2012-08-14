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

import axelson.vaadin.builder.VaadinBuilder;

import com.vaadin.ui.Table
import spock.lang.Specification

class TableTest extends Specification {
	def 'can create a table'() {
		when:
			Table t = new VaadinBuilder().table()
			
		then:
			t && t instanceof Table
	}
	
	def 'can add a column to a table'() {
		when:
			Table t = new VaadinBuilder().table {
                column(propertyId: 'test', header: 'test')
            }
			
		then:
			t.columnHeaders == ['test']
	}
	
	def 'can add an item to a table'() {
		when:
			Table t = new VaadinBuilder().table {
                column(propertyId: 'test', header: 'test')
				item(itemId: 1, test: 'this is a test')
            }
			
		then:
			t.getItem(1)
			t.getItem(1).getItemProperty('test')
			t.getItem(1).getItemProperty('test').value == 'this is a test'
	}
}
