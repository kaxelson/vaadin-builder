import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ExternalResource

new VaadinBuilder().verticalLayout(spacing: true) {
	embedded(
		source: new ExternalResource('http://www.youtube.com/v/meXvxkn1Y_8&hl=en_US&fs=1&'),
		alternateText: 'Vaadin Eclipse Quickstart video',
		mimeType: 'application/x-shockwave-flash',
		parameters: ['allowFullScreen': 'true'],
		width: '320px',
		height: '265px'
	)
}
