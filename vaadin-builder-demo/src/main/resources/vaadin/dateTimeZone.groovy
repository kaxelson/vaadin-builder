import java.text.DateFormat

import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.data.util.IndexedContainer
import com.vaadin.ui.InlineDateField

class TimeZoneData {
	String name
	TimeZone timeZone

	String toString() {name}
}

new VaadinBuilder().verticalLayout(spacing: true) {
	def idf = inlineDateField(caption: 'Please select the starting time:', value: new Date(), resolution: InlineDateField.RESOLUTION_MIN, immediate: true, showISOWeekNumbers: true) {
		attach {
			def w = getWindow()
			valueChange {e ->
				e.property.value
				DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL)
				w.showNotification("Date changed to ${format.format(value)}")
			}
		}
	}
	comboBox(caption: 'Select time zone:', immediate: true, nullSelectionAllowed: false) {
		containerDataSource = new IndexedContainer([
			new TimeZoneData(name: 'UTC', timeZone: TimeZone.getTimeZone('UTC')),
			new TimeZoneData(name: 'Europe/Helsinki', timeZone: TimeZone.getTimeZone('Europe/Helsinki')),
			new TimeZoneData(name: 'Australia/Melbourne', timeZone: TimeZone.getTimeZone('Australia/Melbourne')),
			new TimeZoneData(name: 'America/New_York', timeZone: TimeZone.getTimeZone('America/New_York')),
		])
		valueChange {e ->
			idf.timeZone = e.property.value.timeZone
		}
	}
}
