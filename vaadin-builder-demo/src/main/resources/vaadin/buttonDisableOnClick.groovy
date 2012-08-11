import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.Label

def vl

def clickListener = new VaadinBuilder().buttonClick {e ->
	Thread.sleep(3000)
	vl.addComponent(new Label('Save completed successfully!'))
	e.button.enabled = true
}

vl = new VaadinBuilder().verticalLayout {
	button(caption: 'Save', disableOnClick: true, listener: clickListener)
	nativeButton(caption: 'Save', disableOnClick: true, listener: clickListener)
}
