import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ExternalResource
import com.vaadin.terminal.ThemeResource

new VaadinBuilder().verticalLayout(spacing: true) {
	button(caption: 'Save', icon: new ThemeResource('images/action_save.gif'))
	label(value: 'Icons are very handy', caption: 'Comment', icon: new ThemeResource('images/comment_yellow.gif'))
	panel(caption: 'Handy Links', icon: new ThemeResource('images/icon_info.gif')) {
		link(caption: 'http://vaadin.com', icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://vaadin.com'))
		link(caption: 'http://vaadin.com/learn', icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://vaadin.com/learn'))
		link(caption: 'http://dev.vaadin.com', icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://dev.vaadin.com'))
		link(caption: 'http://vaadin.com/forum', icon: new ThemeResource('images/icon_world.gif'), resource: new ExternalResource('http://vaadin.com/forum'))
	}
}
