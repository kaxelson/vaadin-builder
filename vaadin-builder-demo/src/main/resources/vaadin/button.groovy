import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Label

new VaadinBuilder().horizontalLayout(spacing: true) {
	verticalLayout(spacing: true) {
		label(value: '<h3>Normal buttons</h3>', contentMode: Label.CONTENT_XHTML)
		button(caption: 'Save', description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		button(caption: '<em>Save</em><sup>html</sup>', htmlContentAllowed: true, description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		button(caption: 'Save', icon: new ThemeResource('../images/action_save.gif'), description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		button(icon: new ThemeResource('../images/action_save.gif'), description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
	}

	verticalLayout(spacing: true) {
		label(value: '<h3>Native buttons</h3>', contentMode: Label.CONTENT_XHTML)
		nativeButton(caption: 'Save', description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		nativeButton(caption: '<em>Save</em><sup>html</sup>', htmlContentAllowed: true, description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		nativeButton(caption: 'Save', icon: new ThemeResource('../images/action_save.gif'), description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
		nativeButton(icon: new ThemeResource('../images/action_save.gif'), description: 'Save changes') {
			buttonClick {e -> e.button.window.showNotification('Changes have been saved')}
		}
	}
}
