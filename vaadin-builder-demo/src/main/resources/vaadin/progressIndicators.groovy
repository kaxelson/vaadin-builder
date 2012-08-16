import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Alignment
import com.vaadin.ui.Label

new VaadinBuilder().verticalLayout(spacing: true) {
	label(value: '<strong>Normal mode</strong> Runs for 20 seconds', contentMode: Label.CONTENT_XHTML)
	horizontalLayout(spacing: true) {
		def pi = progressIndicator(indeterminate: false, enabled: false, alignment: Alignment.MIDDLE_LEFT)
		button(caption: 'Start normal', styleName: 'small') {
			buttonClick {e ->
				Thread.start {
					20.times {i ->
						Thread.sleep(1000)
						pi.value = i / 20
					}
					synchronized(e.button.application) {
						pi.enabled = false
						e.button.enabled = true
						pi.value = 1
					}
				}
				pi.enabled = true
				pi.value = 0
				e.button.enabled = false
			}
		}
	}

	label(value: '<strong>Indeterminate mode</strong> Runs for 10 seconds', contentMode: Label.CONTENT_XHTML)
	horizontalLayout(spacing: true) {
		def pi = progressIndicator(indeterminate: true, pollingInterval: 5000, enabled: false, alignment: Alignment.MIDDLE_LEFT)
		button(caption: 'Start indeterminate', styleName: 'small') {
			buttonClick {e ->
				Thread.start {i ->
					Thread.sleep(10000)
					synchronized(e.button.application) {
						pi.enabled = false
						e.button.enabled = true
					}
				}
				pi.enabled = true
				pi.visible = true
				e.button.enabled = false
			}
		}
	}
}
