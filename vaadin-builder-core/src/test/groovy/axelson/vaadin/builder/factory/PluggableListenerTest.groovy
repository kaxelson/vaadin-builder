package axelson.vaadin.builder.factory

import spock.lang.Specification
import axelson.vaadin.builder.factory.listener.Pluggable
import axelson.vaadin.builder.factory.listener.PluggableListeners.PluggableButtonClickListener

import com.vaadin.ui.Button

class PluggableListenerTest extends Specification {
	def 'pluggable listener transform does the right thing'() {
		when:
			def test
			def listener = new PluggableButtonClickListener()
			listener.strategy = {e ->
				test = 'worked'
			}
			listener.buttonClick(null)
			
		then:
			listener instanceof Serializable
			listener instanceof Pluggable
			listener instanceof Button.ClickListener
			listener.serialVersionUID == 1L
			test == 'worked'
	}
	
	def 'can serialize and deserialize a listener'() {
		setup:
			def listener = new PluggableButtonClickListener()
			listener.strategy = {e -> 'test'}.dehydrate()
			
		when:
			def baos = new ByteArrayOutputStream()
			new ObjectOutputStream(baos) << listener
			def bytes = baos.toByteArray()
			
			def bais = new ByteArrayInputStream(bytes)
			def l2 = new ObjectInputStream(bais).readObject()
			
		then:
			l2 && l2 instanceof PluggableButtonClickListener
			l2.strategy != null
			l2.strategy(null) == 'test'
			listener.strategy(null) == l2.strategy(null)
	}
}
