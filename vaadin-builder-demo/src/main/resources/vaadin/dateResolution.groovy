import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.data.util.IndexedContainer
import com.vaadin.ui.InlineDateField

class ResolutionData {
	String name
	Integer resolution

	String toString() {name}
}

new VaadinBuilder().verticalLayout(spacing: true) {
	def idf = inlineDateField(caption: 'Please select the starting time:', value: new Date(), resolution: InlineDateField.RESOLUTION_DAY, immediate: true, showISOWeekNumbers: true)
	comboBox(caption: 'Select resolution:', immediate: true, nullSelectionAllowed: false) {
		containerDataSource = new IndexedContainer([
			new ResolutionData(name: 'Year', resolution: InlineDateField.RESOLUTION_YEAR),
			new ResolutionData(name: 'Month', resolution: InlineDateField.RESOLUTION_MONTH),
			new ResolutionData(name: 'Day', resolution: InlineDateField.RESOLUTION_DAY),
			new ResolutionData(name: 'Hour', resolution: InlineDateField.RESOLUTION_HOUR),
			new ResolutionData(name: 'Minute', resolution: InlineDateField.RESOLUTION_MIN),
			new ResolutionData(name: 'Second', resolution: InlineDateField.RESOLUTION_SEC),
			new ResolutionData(name: 'Millisecond', resolution: InlineDateField.RESOLUTION_MSEC),
		])
		valueChange {e ->
			idf.resolution = e.property.value.resolution
			idf.requestRepaint()
		}
	}
}
