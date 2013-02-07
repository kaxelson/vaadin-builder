import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ExternalResource
import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Link

def caption = 'Open Google in small window'
def description = 'http://www.google.com (opens in small window)'
def icon = new ThemeResource('images/icon_world.gif')
def resource = new ExternalResource('http://www.google.com/m')

new VaadinBuilder().verticalLayout(spacing: true) {
	link(caption: caption, targetName: '_blank', targetWidth: 300, targetHeight: 300, targetBorder: Link.TARGET_BORDER_NONE, resource: resource, description: description)
	link(caption: caption, icon: icon, targetName: '_blank', targetWidth: 300, targetHeight: 300, targetBorder: Link.TARGET_BORDER_NONE, resource: resource, description: description)
	link(icon: icon, targetName: '_blank', targetWidth: 300, targetHeight: 300, targetBorder: Link.TARGET_BORDER_NONE, resource: resource, description: description)
}
