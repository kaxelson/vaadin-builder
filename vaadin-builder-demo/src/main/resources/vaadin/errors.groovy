import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.UserError
import com.vaadin.ui.Label

new VaadinBuilder().verticalLayout(spacing: true) {
	label(value: '<h3>Errors in caption</h3>', contentMode: Label.CONTENT_XHTML)
	label(value: "Error indicators are usually placed on the right side of the component's caption.")
	textField(caption: 'Field caption', componentError: new UserError('This field is never satisfied'))

	label(value: '<h3>Errors with caption</h3>', contentMode: Label.CONTENT_XHTML)
	label(value: "If the component has no caption, the error indicator is usually placed on the right side of the component.")
	textField(inputPrompt: 'This field has an error', componentError: new UserError('This field is never satisfied'))

	label(value: '<h3>Error icon placement depends on the layout</h3>', contentMode: Label.CONTENT_XHTML)
	label(value: "FormLayout for example places the error between the component caption and the actual field.")
	formLayout {
		textField(caption: 'Field caption', inputPrompt: 'This field has an error', componentError: new UserError('This field is never satisfied'))
	}
}
