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

import com.vaadin.ui.ComboBox
import com.vaadin.ui.ListSelect
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.OptionGroup
import com.vaadin.ui.Select

class SelectTest extends Specification {
	def 'can create a new Select'(Class type) {
		when:
			def node = toCamelCase(type.simpleName)
			def caption = "Test ${node}"
			def f = new VaadinBuilder()."${node}"(caption: caption, options: 1..5)

		then:
			f && f.class == type
			f.caption == caption
			f.itemIds as Set == 1..5 as Set

		where:
			type << [ListSelect, NativeSelect, OptionGroup, Select, ComboBox]
	}

	private String toCamelCase(String s) {
		if (s == null) throw new IllegalArgumentException('Cannot convert null String to camel case.')
		if (s.size() == 0) return s
		s[0].toLowerCase() + ((s.size() > 1) ? s[1..-1] : '')
	}
}
