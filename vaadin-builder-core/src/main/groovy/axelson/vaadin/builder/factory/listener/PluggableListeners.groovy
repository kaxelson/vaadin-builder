package axelson.vaadin.builder.factory.listener

import com.vaadin.data.Container
import com.vaadin.data.Property
import com.vaadin.event.FieldEvents
import com.vaadin.event.ItemClickEvent
import com.vaadin.event.LayoutEvents
import com.vaadin.event.MouseEvents
import com.vaadin.terminal.Paintable
import com.vaadin.ui.AbstractSplitPanel
import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.ComponentContainer
import com.vaadin.ui.LoginForm
import com.vaadin.ui.PopupView
import com.vaadin.ui.TabSheet
import com.vaadin.ui.Table
import com.vaadin.ui.Tree
import com.vaadin.ui.Upload
import com.vaadin.ui.UriFragmentUtility
import com.vaadin.ui.Window

//TODO: Add listeners for SQLContainer and Application
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
	@PluggableListener(Window.ResizeListener) static class PluggableWindowResizeListener {}
	@PluggableListener(Upload.FailedListener) static class PluggableUploadFailedListener {}
	@PluggableListener(Upload.FinishedListener) static class PluggableUploadFinishedListener {}
	@PluggableListener(Upload.StartedListener) static class PluggableUploadStartedListener {}
	@PluggableListener(Upload.SucceededListener) static class PluggableUploadSucceededListener {}
	@PluggableListener(Tree.CollapseListener) static class PluggableTreeCollapseListener {}
	@PluggableListener(Tree.ExpandListener) static class PluggableTreeExpandListener {}
	@PluggableListener(ItemClickEvent.ItemClickListener) static class PluggableItemClickListener {}
	@PluggableListener(TabSheet.SelectedTabChangeListener) static class PluggableSelectedTabChangeListener {}
	@PluggableListener(Table.ColumnReorderListener) static class PluggableTableColumnReorderListener {}
	@PluggableListener(Table.ColumnResizeListener) static class PluggableTableColumnResizeListener {}
	@PluggableListener(Table.FooterClickListener) static class PluggableTableFooterClickListener {}
	@PluggableListener(Table.HeaderClickListener) static class PluggableTableHeaderClickListener {}
	@PluggableListener(PopupView.PopupVisibilityListener) static class PluggablePopupVisibilityListener {}
	@PluggableListener(MouseEvents.ClickListener) static class PluggableMouseClickListener {}
	@PluggableListener(LoginForm.LoginListener) static class PluggableLoginListener {}
	@PluggableListener(FieldEvents.TextChangeListener) static class PluggableTextChangeListener {}
	@PluggableListener(AbstractSplitPanel.SplitterClickListener) static class PluggableSplitterClickListener {}
	@PluggableListener(ComponentContainer.ComponentAttachListener) static class PluggableComponentAttachListener {}
	@PluggableListener(ComponentContainer.ComponentDetachListener) static class PluggableComponentDetachListener {}
	@PluggableListener(Paintable.RepaintRequestListener) static class PluggableRepaintRequestListener {}
	@PluggableListener(Component.Listener) static class PluggableComponentListener {}
	@PluggableListener(UriFragmentUtility.FragmentChangedListener) static class PluggableFragmentChangedListener {}
	
	static class PluggableUploadProgressListener extends Pluggable implements Upload.ProgressListener, Serializable {
//		static final long serialVersionUID = 1L

		@Override
		public void updateProgress(long readBytes, long contentLength) {
			execute([readBytes, contentLength])
		}
	}
}
