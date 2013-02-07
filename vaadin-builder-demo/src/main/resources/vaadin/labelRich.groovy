import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Label

def text = '''
	<h1>Rich text example</h1>
	<p>The <b>quick</b> brown fox jumps <sup>over</sup> the <b>lazy</b> dog.</p>
	<p>This text can be edited with the <i>Edit</i> -button</p>
'''.stripIndent().trim()

def rta = new VaadinBuilder().richTextArea(width: '100%')

new VaadinBuilder().verticalLayout(spacing: true) {
	def l = label(value: text, contentMode: Label.CONTENT_XHTML)
	button(caption: 'Edit') {
		buttonClick {e ->
			def vl = e.button.parent
			if (vl.componentIterator.next() == l) {
				rta.value = l.value
				vl.replaceComponent(l, rta)
				e.button.caption = "Apply"
			} else {
				l.value = rta.value
				vl.replaceComponent(rta, l)
				e.button.caption = "Edit"
			}
		}
	}
}
