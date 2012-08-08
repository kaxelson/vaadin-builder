import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Button
import com.vaadin.ui.Window

verticalLayout {
	def vl = current
	button(caption: 'Click for new Window') {
		buttonClick {Button.ClickEvent event ->
			Window w = new VaadinBuilder().window(caption: 'Test Window', positionX: 100, positionY: 100) {
				windowClose {Window.CloseEvent e ->
					vl.application.mainWindow.removeWindow(e.window)
				}
			}
			vl.application.mainWindow.addWindow(w)
		}
	}
}
