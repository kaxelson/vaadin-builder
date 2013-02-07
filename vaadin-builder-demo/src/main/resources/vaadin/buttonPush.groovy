import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Label
import com.vaadin.ui.Layout

def clickListener = new VaadinBuilder().buttonClick {e ->
	e.button.window.showNotification('Changes have been saved')
}

new VaadinBuilder().horizontalLayout(spacing: true) {
	verticalLayout(spacing: true, margin: new Layout.MarginInfo(false, true, false, false)) {
		label(value: '<h3>Normal buttons</h3>', contentMode: Label.CONTENT_XHTML)
		button(caption: 'Save', description: 'Save changes', listener: clickListener)
		button(caption: '<em>Save</em><sup>html</sup>', htmlContentAllowed: true, description: 'Save changes', listener: clickListener)
		button(caption: 'Save', icon: new ThemeResource('images/action_save.gif'), description: 'Save changes', listener: clickListener)
		button(icon: new ThemeResource('images/action_save.gif'), description: 'Save changes', listener: clickListener)
	}

	verticalLayout(spacing: true, margin: new Layout.MarginInfo(false, false, false, true)) {
		label(value: '<h3>Native buttons</h3>', contentMode: Label.CONTENT_XHTML)
		nativeButton(caption: 'Save', description: 'Save changes', listener: clickListener)
		nativeButton(caption: '<em>Save</em><sup>html</sup>', htmlContentAllowed: true, description: 'Save changes', listener: clickListener)
		nativeButton(caption: 'Save', icon: new ThemeResource('images/action_save.gif'), description: 'Save changes', listener: clickListener)
		nativeButton(icon: new ThemeResource('images/action_save.gif'), description: 'Save changes', listener: clickListener)
	}
}
