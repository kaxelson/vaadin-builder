import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ExternalResource
import com.vaadin.terminal.ThemeResource

new VaadinBuilder().verticalLayout(spacing: true) {
	link(caption: 'Open Google', resource: new ExternalResource('http://www.google.com'), description: 'http://www.google.com')
	link(caption: 'Open Google', icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://www.google.com'), description: 'http://www.google.com')
	link(icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://www.google.com'), description: 'http://www.google.com')
}
