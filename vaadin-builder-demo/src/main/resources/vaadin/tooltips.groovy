import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.themes.BaseTheme

String editTxt = 'Edit tooltip'
String applyTxt = 'Apply'

new VaadinBuilder().verticalLayout(spacing: true) {
	button(caption: 'Mouse over for plain tooltip', styleName: BaseTheme.BUTTON_LINK, description: 'A simple plaintext tooltip')
	button(caption: 'Mouse over for richtext tooltip', styleName: BaseTheme.BUTTON_LINK, description: 'A simple plaintext tooltip') {
		attach {b ->
			b.description = """
				<h2><img src="${b.application.getURL()}VAADIN/themes/vbd/images/comment_yellow.gif" />A richtext tooltip</h2>
                <ul>
					<li>HTML formatting</li>
					<li>Images<br/></li>
					<li>etc...</li>
				</ul>
			""".stripIndent().trim()
		}
	}
	def rta = richTextArea(width: '100%', visible: false, value: "Click <b>$editTxt</b> to edit this tooltip, then <b>$applyTxt</b>")
	button(caption: editTxt, description: rta.value) {
		buttonClick {e ->
			if (rta.visible) {
				e.button.description = rta.value
				e.button.caption = editTxt
			} else {
				e.button.caption = applyTxt
			}
			rta.visible = !rta.visible
		}
	}
}
