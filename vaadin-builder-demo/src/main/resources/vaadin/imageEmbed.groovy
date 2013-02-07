import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource

new VaadinBuilder().verticalLayout(spacing: true) {
	embedded(caption: 'Image from a theme resource', source: new ThemeResource('../runo/icons/64/document.png'), alternateText: 'Document icon from the Runo theme')
}
