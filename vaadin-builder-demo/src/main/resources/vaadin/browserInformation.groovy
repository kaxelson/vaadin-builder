import axelson.vaadin.builder.VaadinBuilder

import com.vaadin.terminal.gwt.server.WebBrowser
import com.vaadin.ui.Label

String getTimeZoneInfoString(WebBrowser wb) {
	int tzOffset = wb.timezoneOffset
	String infoStr = String.format('Your browser indicates GMT%s%d', (tzOffset < 0 ? '-' : '+'), Math.abs(tzoToHours(tzOffset)))
	if (wb.DSTInEffect) {
		infoStr += String.format(' and DST %d', tzoToHours(wb.DSTSavings))
	}
	infoStr + ', which could mean:'
}

int tzoToHours(int ms) {
	ms / 1000 / 60 / 60
}

String getOperatingSystem(WebBrowser wb) {
	[Windows: wb.windows, 'Mac OSX': wb.macOSX, Linux: wb.linux].
		find {it.value}?.key ?: 'an unknown operating system'
}

String getBrowserAndVersion(WebBrowser wb) {
	[Chrome: wb.chrome, Opera: wb.opera, Firefox: wb.firefox, Safari: wb.safari, 'Internet Explorer': wb.IE].
		find {it.value}?.with {
			"${it.key} ${wb.browserMajorVersion}.${wb.browserMinorVersion}"
		} ?: 'an unknown browser'
}

new VaadinBuilder().verticalLayout {
	attach {
		WebBrowser wb = application.context.browser
		label(value: "Hello user from <b>${wb.address}</b>.", contentMode: Label.CONTENT_XHTML)
		label(value: "You are running <b>${getBrowserAndVersion(wb)} in ${getOperatingSystem(wb)}</b>.", contentMode: Label.CONTENT_XHTML)
		label(value: "Your screen resolution is <b>${wb.screenWidth}x${wb.screenHeight}</b>.", contentMode: Label.CONTENT_XHTML)
		label(value: "Your browser is set to primarily use the <b>${wb.locale}</b> locale.", contentMode: Label.CONTENT_XHTML)
		def ns = nativeSelect(caption: getTimeZoneInfoString(wb), immediate: true, nullSelectionAllowed: false) {
			TimeZone.getAvailableIDs(wb.rawTimezoneOffset).each {tzid ->
				TimeZone tz = TimeZone.getTimeZone(tzid)
				if (wb.DSTSavings == tz.DSTSavings) {
					item(itemId: "$tzid (${tz.displayName})")
				}
			}
		}
		ns.value = ns.itemIds.find()
	}
}
