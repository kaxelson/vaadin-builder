import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.themes.BaseTheme

def clickListener = new VaadinBuilder().buttonClick {e ->
	e.button.window.showNotification('Help clicked')
}

new VaadinBuilder().verticalLayout(spacing: true) {
	button(caption: 'Help', styleName: BaseTheme.BUTTON_LINK, description: 'Show help', listener: clickListener)
	button(caption: 'Help', icon: new ThemeResource('images/icon_info.gif'), styleName: BaseTheme.BUTTON_LINK, description: 'Show help', listener: clickListener)
	button(icon: new ThemeResource('images/icon_info.gif'), styleName: BaseTheme.BUTTON_LINK, description: 'Show help', listener: clickListener)
}
