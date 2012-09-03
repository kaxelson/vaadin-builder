import java.text.DateFormat

import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.ui.InlineDateField

new VaadinBuilder().verticalLayout(spacing: true) {
	inlineDateField(caption: 'Please select the starting time:', value: new Date(), resolution: InlineDateField.RESOLUTION_DAY, immediate: true) {
		attach {
			def w = getWindow()
			valueChange {e ->
				DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
				w.showNotification("Starting date: ${dateFormatter.format(e.property.value)}")
			}
		}
	}
}
