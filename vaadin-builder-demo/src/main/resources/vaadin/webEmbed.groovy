import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ExternalResource
import com.vaadin.ui.Embedded

new VaadinBuilder().verticalLayout(spacing: true) {
	embedded(
		caption: 'Vaadin web site',
		source: new ExternalResource('http://vaadin.com'),
		alternateText: 'Vaadin web site',
		type: Embedded.TYPE_BROWSER,
		width: '100%',
		height: '400px'
	)
}
