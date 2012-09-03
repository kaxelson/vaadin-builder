import java.text.DateFormat

import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.PopupDateField

new VaadinBuilder().verticalLayout(spacing: true) {
	popupDateField(inputPrompt: 'Start date', resolution: PopupDateField.RESOLUTION_DAY, immediate: true) {
		attach {
			def w = getWindow()
			valueChange {e ->
				DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
				Object value = e.property.value
				if (value == null || !(value instanceof Date)) {
					w.showNotification('Invalid date entered')
				} else {
					w.showNotification("Starting date: ${dateFormatter.format(value)}")
				}
			}
		}
	}
}
