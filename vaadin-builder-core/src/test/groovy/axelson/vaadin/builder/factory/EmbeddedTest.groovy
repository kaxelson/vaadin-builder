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

import com.vaadin.terminal.ExternalResource
import com.vaadin.ui.Embedded

class EmbeddedTest extends Specification {
	def 'can create an embedded'() {
		when:
			Embedded e = new VaadinBuilder().embedded(source: new ExternalResource('http://www.google.com'))

		then:
			e && e instanceof Embedded
			e.source && e.source instanceof ExternalResource
			e.source.getURL() == 'http://www.google.com'
	}

	def 'can set parameters'() {
		when:
			Embedded e = new VaadinBuilder().embedded(source: new ExternalResource('http://www.google.com'), parameters: ['test': 'value'])

		then:
			e && e instanceof Embedded
			e.parameterNames.toList().contains('test')
			e.getParameter('test') == 'value'
	}

	def 'can load flash content'() {
		when:
			Embedded e
			new VaadinBuilder().verticalLayout(spacing: true) {
				e = embedded(
					source: new ExternalResource('http://www.youtube.com/v/meXvxkn1Y_8&hl=en_US&fs=1&'),
					alternateText: 'Vaadin Eclipse Quickstart video',
					mimeType: 'application/x-shockwave-flash',
					parameters: ['allowFullScreen': 'true'],
					width: '320px',
					height: '265px'
				)
			}

		then:
			e && e instanceof Embedded
			e.parameterNames.toList().contains('allowFullScreen')
			e.getParameter('allowFullScreen') == 'true'
	}
}
