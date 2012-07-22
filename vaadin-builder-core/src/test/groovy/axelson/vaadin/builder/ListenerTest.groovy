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

import com.vaadin.event.LayoutEvents.LayoutClickEvent
import com.vaadin.terminal.gwt.client.MouseEventDetails
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener

class ListenerTest extends Specification {
	def 'can create a detached button click listener instance'() {
		when:
			ClickListener l = new VaadinBuilder().buttonClick {e ->}
			
		then:
			l && l instanceof ClickListener
			l instanceof Serializable
	}
	
	def 'can add a click listener to a button'() {
		setup:
			String test = null
			Button b = new VaadinBuilder().button {
				buttonClick {e ->
					test = 'clicked'
				}
			}
			
		when:
			b.click()
			
		then:
			b.getListeners(Button.ClickEvent).size() == 1
			test == 'clicked'
	}
	
	def 'can add a click listener to a layout'() {
		setup:
			String test = null
			AbsoluteLayout l = new VaadinBuilder().absoluteLayout {
				layoutClick {e ->
					test = 'clicked'
				}
			}
			
		when:
	        l.fireEvent(new LayoutClickEvent(l, new MouseEventDetails(), l, l))
			
		then:
			l.getListeners(LayoutClickEvent).size() == 1
			test == 'clicked'
	}
	
	def 'can serialize and deserialize a listener'() {
		setup:
			def l = new VaadinBuilder().buttonClick {e -> 'test'}
			
		when:
			def baos = new ByteArrayOutputStream()
			new ObjectOutputStream(baos) << l
			def bytes = baos.toByteArray()
			
			def bais = new ByteArrayInputStream(bytes)
			def l2 = new ObjectInputStream(bais).readObject()
			
		then:
			l2.strategy != null
			l2.strategy() == 'test'
			l.strategy() == l2.strategy()
	}
}
