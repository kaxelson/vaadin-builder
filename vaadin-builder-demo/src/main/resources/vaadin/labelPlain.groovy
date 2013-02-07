import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Label

def text = '''
	This is an example of a Label
	component. The content mode of this label is set
	to CONTENT_TEXT. This means that it will display
	the content text as is. HTML and XML special characters
	(<,>,&) are escaped properly to allow displaying them.
'''

new VaadinBuilder().verticalLayout(spacing: true) {
	label(value: text, contentMode: Label.CONTENT_TEXT)
}
