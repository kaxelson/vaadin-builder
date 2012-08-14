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

import com.vaadin.ui.AbstractOrderedLayout
import com.vaadin.ui.Alignment
import com.vaadin.ui.Layout
import com.vaadin.ui.Panel
import com.vaadin.ui.TextArea
import com.vaadin.ui.Layout.MarginHandler

class PanelTest extends Specification {
	def 'can create a new panel'() {
		when:
			Panel p = new VaadinBuilder().panel()

		then:
			p && p instanceof Panel
	}

	def 'can set expand ratio'() {
		when:
			TextArea ta1
			TextArea ta2
			Panel p = new VaadinBuilder().panel(width: '100%') {
				ta1 = textArea(expandRatio: 1)
				ta2 = textArea(expandRatio: 2)
			}

		then:
			p.content instanceof AbstractOrderedLayout
			p.content.getExpandRatio(ta1) == 1
			p.content.getExpandRatio(ta2) == 2
	}

	def 'can set alignment'() {
		when:
			TextArea ta1
			TextArea ta2
			Panel p = new VaadinBuilder().panel(width: '100%') {
				ta1 = textArea(alignment: Alignment.TOP_LEFT)
				ta2 = textArea(alignment: Alignment.BOTTOM_RIGHT)
			}

		then:
			p.content instanceof Layout.AlignmentHandler
			p.content.getComponentAlignment(ta1) == Alignment.TOP_LEFT
			p.content.getComponentAlignment(ta2) == Alignment.BOTTOM_RIGHT
	}

	def 'can set spacing'() {
		when:
			Panel p = new VaadinBuilder().panel(spacing: true)

		then:
			p.content instanceof Layout.SpacingHandler
			p.content.spacing == true
	}

	def 'can set margin'() {
		when:
			Panel p = new VaadinBuilder().panel(margin: false) //setting to false since true is the default

		then:
			p.content instanceof Layout.MarginHandler
			p.content.margin && p.content.margin instanceof Layout.MarginInfo
			!p.content.margin.hasBottom() && !p.content.margin.hasLeft() && !p.content.margin.hasRight() && !p.content.margin.hasTop()
	}
}
