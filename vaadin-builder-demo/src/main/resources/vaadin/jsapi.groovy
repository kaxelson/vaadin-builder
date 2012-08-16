import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Label

// This label will be show for 10 seconds while the background process is working
def runningLabel = new VaadinBuilder().label(
	caption: 'Background process is running for 10 seconds, click the link below',
	icon: new ThemeResource('../base/common/img/ajax-loader-medium.gif')
)

new VaadinBuilder().verticalLayout(spacing: true) {
	label(value: '<h3>Run Native JavaScript</h3>', contentMode: Label.CONTENT_XHTML)
	def ta = textArea(width: '100%', rows: 3, value: 'alert("Hello Vaadin")')
	button(caption: 'Run script') {
		buttonClick {e ->
			e.button.window.executeJavaScript(ta.value)
		}
	}

	label(value: '<h3>Force Server Syncronization</h3>', contentMode: Label.CONTENT_XHTML)
	label {
		value = '''
			For advanced client side programmers Vaadin offers a simple
			method which can be used to force the client to synchronize with the
			server. This may be needed for example if another part of a mashup
			changes things on server.
		'''.stripIndent().replaceAll('\n',' ').trim()
	}
	def targetLabel = label(contentMode: Label.CONTENT_XHTML) {
		value = 'This Label component will be updated by a background thread. Click "Start ' +
            'background thread" button and start clicking on the link below to force ' +
            'synchronization.'
	}
	// Clicking on this button will start a repeating thread that updates the label value
	button(caption: 'Start background thread') {
		buttonClick {e ->
			e.button.parent.replaceComponent(e.button, runningLabel)
			Thread.start {
				10.times {
					Thread.sleep(1000)
					targetLabel.value = "<strong>Server time is ${new Date().format('HH:mm:ss')}</strong>"
				}
				targetLabel.value = 'Background process finished'
				runningLabel.parent.replaceComponent(runningLabel, e.button)
			}
		}
	}
	// This link will make an Ajax request to the server that will respond with UI changes that have happened since last request
	label(value: '<a href="javascript:vaadin.forceSync();">javascript: vaadin.forceSync();</a>', contentMode: Label.CONTENT_XHTML)
}
