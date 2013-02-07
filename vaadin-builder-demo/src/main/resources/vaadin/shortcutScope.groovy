import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.event.ShortcutListener
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.ModifierKey
import com.vaadin.ui.AbstractField.FocusShortcut
import com.vaadin.ui.Button.ClickShortcut
import com.vaadin.ui.Component.Focusable

new VaadinBuilder().verticalLayout(spacing: true) {
	horizontalLayout(spacing: true) {
		(1..2).each {i ->
			panel(caption: "Panel $i", spacing: true, description: "CTRL-$i to focus") {
				def fn = textField(caption: 'Firstname', inputPrompt: 'ALT-SHIFT-F to focus') {
					shortcutListener(new FocusShortcut(current, "Focus panel ^$i"))
				}
				action(new FocusShortcut(fn, KeyCode.F, ModifierKey.ALT, ModifierKey.SHIFT))

				def ln = textField(caption: 'Lastname', inputPrompt: 'ALT-SHIFT-L to focus')
				action(new FocusShortcut(ln, KeyCode.L, ModifierKey.ALT, ModifierKey.SHIFT))

				def b = button(caption: 'Enter', styleName: 'primary') {
					buttonClick {it.button.window.showNotification('Enter button clicked')}
				}
				action(new ClickShortcut(b, KeyCode.S, ModifierKey.ALT, ModifierKey.SHIFT))

				action(new ShortcutListener('Next field', KeyCode.ARROW_DOWN, null) {
					void handleAction(Object sender, Object target) {
						boolean found = false
						sender.componentIterator.find {c ->
							if (found && c instanceof Focusable) {
								c.focus()
								return true
							} else if (c == target) {
								found = true
							}
							return false
						}
					}
				})
			}
		}
	}
}