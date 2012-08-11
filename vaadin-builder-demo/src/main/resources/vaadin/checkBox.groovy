import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource

def clickListener = new VaadinBuilder().buttonClick {e ->
	boolean enabled = e.button.booleanValue()
	e.button.window.showNotification('HTML ' + (enabled ? 'enabled' : 'disabled'))
}

new VaadinBuilder().verticalLayout(spacing: true) {
	checkBox(caption: 'Allow HTML', description: 'Allow/disallow HTML in comments', immediate: true, listener: clickListener)
	checkBox(caption: 'Allow HTML', icon: new ThemeResource('images/page_code.gif'), description: 'Allow/disallow HTML in comments', immediate: true, listener: clickListener)
	checkBox(icon: new ThemeResource('images/page_code.gif'), description: 'Allow/disallow HTML in comments', immediate: true, listener: clickListener)
}
