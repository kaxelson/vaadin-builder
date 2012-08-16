import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Alignment
import com.vaadin.ui.themes.Reindeer

def icons = ['arrow-down.png', 'arrow-left.png',
	'arrow-right.png', 'arrow-up.png', 'attention.png', 'calendar.png',
	'cancel.png', 'document.png', 'document-add.png',
	'document-delete.png', 'document-doc.png', 'document-image.png',
	'document-pdf.png', 'document-ppt.png', 'document-txt.png',
	'document-web.png', 'document-xsl.png', 'email.png',
	'email-reply.png', 'email-send.png', 'folder.png',
	'folder-add.png', 'folder-delete.png', 'globe.png', 'help.png',
	'lock.png', 'note.png', 'ok.png', 'reload.png', 'settings.png',
	'trash.png', 'trash-full.png', 'user.png', 'users.png']

new VaadinBuilder().verticalLayout(spacing: true) {
	tabSheet(styleName: Reindeer.TABSHEET_MINIMAL) {
		[16, 32, 64].each {size ->
			tab(caption: size + 'x' + size) {
				gridLayout(spacing: true, margin: true, columns: (size == 64) ? 4 : 6) {
					icons.each {icon ->
						embedded(width: size + 'px', height: size + 'px', source: new ThemeResource("../runo/icons/$size/$icon"))
						label(value: icon, width: (size == 64) ? '185px' : '150px', alignment: Alignment.MIDDLE_LEFT)
					}
				}
			}
		}
	}
}
