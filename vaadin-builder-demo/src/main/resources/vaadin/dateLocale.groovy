import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.data.util.IndexedContainer
import com.vaadin.ui.InlineDateField

class LocaleData {
	String name
	Locale locale

	String toString() {name}
}

new VaadinBuilder().verticalLayout(spacing: true) {
	def idf = inlineDateField(caption: 'Please select the starting time:', value: new Date(), resolution: InlineDateField.RESOLUTION_MIN, immediate: true, showISOWeekNumbers: true)
	comboBox(caption: 'Select date format:', immediate: true, nullSelectionAllowed: false) {
		containerDataSource = new IndexedContainer([
			new LocaleData(name: 'Finnish', locale: Locale.getInstance('fi', 'FI', '')),
			new LocaleData(name: 'German', locale: Locale.GERMANY),
			new LocaleData(name: 'US - English', locale: Locale.US),
			new LocaleData(name: 'Swedish', locale: Locale.getInstance('sv', 'SE', '')),
		])
		valueChange {e ->
			idf.locale = e.property.value.locale
			idf.requestRepaint()
		}
	}
}
