import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Label

def text = '''
	This is an example of a Label component.

	The content mode of this label is set
	to CONTENT_PREFORMATTED. This means
	that it will display the content text
	using a fixed-width font. You also have
	to insert the line breaks yourself.

		HTML and XML special characters
		(<,>,&) are escaped properly to
		allow displaying them.
'''.stripIndent().trim()

new VaadinBuilder().verticalLayout(spacing: true) {
	label(value: text, contentMode: Label.CONTENT_PREFORMATTED)
}
