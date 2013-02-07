import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.ModifierKey
import com.vaadin.ui.AbstractField.FocusShortcut

new VaadinBuilder().verticalLayout(spacing: true) {
	textField(caption: 'Firstname', inputPrompt: 'ALT-SHIFT-F to focus') {
		shortcutListener(new FocusShortcut(current, KeyCode.F, ModifierKey.ALT, ModifierKey.SHIFT))
	}
	textField(caption: 'Lastname', inputPrompt: 'ALT-SHIFT-L to focus') {
		shortcutListener(new FocusShortcut(current, KeyCode.L, ModifierKey.ALT, ModifierKey.SHIFT))
	}
	button(caption: 'Enter', styleName: 'primary') {
		current.setClickShortcut(KeyCode.ENTER)
		buttonClick {e ->
			e.button.window.showNotification('Enter button clicked')
		}
	}
}
