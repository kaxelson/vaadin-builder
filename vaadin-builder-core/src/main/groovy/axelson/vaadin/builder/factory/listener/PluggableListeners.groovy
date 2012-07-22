package axelson.vaadin.builder.factory.listener

import com.vaadin.data.Container
import com.vaadin.data.Property
import com.vaadin.event.FieldEvents
import com.vaadin.event.LayoutEvents
import com.vaadin.ui.Button
import com.vaadin.ui.Window

class PluggableListeners {
	@PluggableListener(Button.ClickListener) static class PluggableButtonClickListener {}
	@PluggableListener(LayoutEvents.LayoutClickListener) static class PluggableLayoutClickListener {}
	@PluggableListener(FieldEvents.BlurListener) static class PluggableBlurListener {}
	@PluggableListener(FieldEvents.FocusListener) static class PluggableFocusListener {}
	@PluggableListener(Property.ReadOnlyStatusChangeListener) static class PluggableReadOnlyStatusChangeListener {}
	@PluggableListener(Property.ValueChangeListener) static class PluggableValueChangeListener {}
	@PluggableListener(Container.ItemSetChangeListener) static class PluggableItemSetChangeListener {}
	@PluggableListener(Container.PropertySetChangeListener) static class PluggablePropertySetChangeListener {}
	@PluggableListener(Window.CloseListener) static class PluggableWindowCloseListener {}
}
