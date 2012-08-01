/*
 * Copyright 2012 Knute G. Axelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package axelson.vaadin.builder

import axelson.vaadin.builder.factory.ColumnFactory
import axelson.vaadin.builder.factory.ComponentContainerFactory
import axelson.vaadin.builder.factory.ComponentFactory
import axelson.vaadin.builder.factory.FieldFactory
import axelson.vaadin.builder.factory.FormFactory
//import axelson.vaadin.builder.factory.FormFieldFactory
import axelson.vaadin.builder.factory.ItemFactory
import axelson.vaadin.builder.factory.LayoutFactory
import axelson.vaadin.builder.factory.MediaFactory
import axelson.vaadin.builder.factory.SelectFactory
import axelson.vaadin.builder.factory.SimpleComponentFactory
import axelson.vaadin.builder.factory.TabFactory
import axelson.vaadin.builder.factory.TabSheetFactory
import axelson.vaadin.builder.factory.TableFactory
import axelson.vaadin.builder.factory.WindowFactory
import axelson.vaadin.builder.factory.listener.ListenerFactory
import axelson.vaadin.builder.factory.listener.PluggableListeners

import com.vaadin.data.Container
import com.vaadin.data.Property
import com.vaadin.event.FieldEvents
import com.vaadin.event.LayoutEvents
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.Accordion
import com.vaadin.ui.Audio
import com.vaadin.ui.Button
import com.vaadin.ui.CheckBox
import com.vaadin.ui.ComboBox
import com.vaadin.ui.CssLayout
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.CustomLayout
import com.vaadin.ui.DateField
import com.vaadin.ui.DragAndDropWrapper
import com.vaadin.ui.Embedded
import com.vaadin.ui.Form
import com.vaadin.ui.FormLayout
import com.vaadin.ui.GridLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.HorizontalSplitPanel
import com.vaadin.ui.InlineDateField
import com.vaadin.ui.Label
import com.vaadin.ui.Link
import com.vaadin.ui.ListSelect
import com.vaadin.ui.LoginForm
import com.vaadin.ui.MenuBar
import com.vaadin.ui.NativeButton
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.OptionGroup
import com.vaadin.ui.Panel
import com.vaadin.ui.PasswordField
import com.vaadin.ui.PopupDateField
import com.vaadin.ui.PopupView
import com.vaadin.ui.ProgressIndicator
import com.vaadin.ui.RichTextArea
import com.vaadin.ui.Select
import com.vaadin.ui.Slider
import com.vaadin.ui.TabSheet
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.Tree
import com.vaadin.ui.TreeTable
import com.vaadin.ui.TwinColSelect
import com.vaadin.ui.Upload
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.VerticalSplitPanel
import com.vaadin.ui.Video

class VaadinBuilder extends FactoryBuilderSupport implements Serializable {
	public static final Factory BUTTON_FACTORY = new FieldFactory(Button)
	public static final Factory NATIVE_BUTTON_FACTORY = new FieldFactory(NativeButton)
	public static final Factory CHECK_BOX_FACTORY = new FieldFactory(CheckBox)
	public static final Factory DATE_FIELD_FACTORY = new FieldFactory(DateField)
	public static final Factory POPUP_DATE_FIELD_FACTORY = new FieldFactory(PopupDateField)
	public static final Factory INLINE_DATE_FIELD_FACTORY = new FieldFactory(InlineDateField)
	public static final Factory RICH_TEXT_AREA_FACTORY = new FieldFactory(RichTextArea)
	public static final Factory TEXT_FIELD_FACTORY = new FieldFactory(TextField)
	public static final Factory TEXT_AREA_FACTORY = new FieldFactory(TextArea)
	public static final Factory PASSWORD_FIELD_FACTORY = new FieldFactory(PasswordField)
	public static final Factory PROGRESS_INDICATOR_FACTORY = new FieldFactory(ProgressIndicator)
	public static final Factory SLIDER_FACTORY = new FieldFactory(Slider)

	public static final Factory SELECT_FACTORY = new SelectFactory(Select)
	public static final Factory COMBO_BOX_FACTORY = new SelectFactory(ComboBox)
	public static final Factory LIST_SELECT_FACTORY = new SelectFactory(ListSelect)
	public static final Factory NATIVE_SELECT_FACTORY = new SelectFactory(NativeSelect)
	public static final Factory TWIN_COL_SELECT_FACTORY = new SelectFactory(TwinColSelect)
	public static final Factory OPTION_GROUP_FACTORY = new SelectFactory(OptionGroup)
	public static final Factory TREE_FACTORY = new SelectFactory(Tree)
	public static final Factory TREE_TABLE_FACTORY = new SelectFactory(TreeTable)

	public static final Factory EMBEDDED_FACTORY = new ComponentFactory(Embedded)
	public static final Factory LINK_FACTORY = new ComponentFactory(Link)
	public static final Factory LABEL_FACTORY = new ComponentFactory(Label)
	public static final Factory MENU_BAR_FACTORY = new ComponentFactory(MenuBar)
	public static final Factory UPLOAD_FACTORY = new ComponentFactory(Upload)

	public static final Factory AUDIO_FACTORY = new MediaFactory(Audio)
	public static final Factory VIDEO_FACTORY = new MediaFactory(Video)

	public static final Factory PANEL_FACTORY = new ComponentContainerFactory(Panel)
	public static final Factory TAB_SHEET_FACTORY = new TabSheetFactory(TabSheet)
	public static final Factory TAB_FACTORY = new TabFactory()
	public static final Factory ACCORDION_FACTORY = new TabSheetFactory(Accordion)
	public static final Factory POPUP_VIEW_FACTORY = new ComponentContainerFactory(PopupView)
	public static final Factory LOGIN_FORM_FACTORY = new ComponentContainerFactory(LoginForm)
	public static final Factory CUSTOM_COMPONENT_FACTORY = new ComponentContainerFactory(CustomComponent)
	public static final Factory DRAG_AND_DROP_WRAPPER_FACTORY = new ComponentContainerFactory(DragAndDropWrapper)

	public static final Factory ABSOLUTE_LAYOUT_FACTORY = new LayoutFactory(AbsoluteLayout)
	public static final Factory GRID_LAYOUT_FACTORY = new LayoutFactory(GridLayout)
	public static final Factory HORIZONTAL_SPLIT_PANEL_FACTORY = new LayoutFactory(HorizontalSplitPanel)
	public static final Factory VERTICAL_SPLIT_PANEL_FACTORY = new LayoutFactory(VerticalSplitPanel)
	public static final Factory CUSTOM_LAYOUT_FACTORY = new LayoutFactory(CustomLayout)
	public static final Factory FORM_LAYOUT_FACTORY = new LayoutFactory(FormLayout)
	public static final Factory HORIZONTAL_LAYOUT_FACTORY = new LayoutFactory(HorizontalLayout)
	public static final Factory VERTICAL_LAYOUT_FACTORY = new LayoutFactory(VerticalLayout)
	public static final Factory CSS_LAYOUT_FACTORY = new LayoutFactory(CssLayout)

	public static final Factory FORM_FACTORY = new FormFactory()
//	public static final Factory FORM_FIELD_FACTORY = new FormFieldFactory()

	public static final Factory COMPONENT_FACTORY = new SimpleComponentFactory()

	public static final Factory WINDOW_FACTORY = new WindowFactory()

	public static final Factory TABLE_FACTORY = new TableFactory()
	public static final Factory COLUMN_FACTORY = new ColumnFactory()

	public static final Factory ITEM_FACTORY = new ItemFactory()

	public static final Factory BUTTON_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableButtonClickListener)
	public static final Factory LAYOUT_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableLayoutClickListener)
	public static final Factory BLUR_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableBlurListener)
	public static final Factory FOCUS_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableFocusListener)
	public static final Factory READ_ONLY_STATUS_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableReadOnlyStatusChangeListener)
	public static final Factory VALUE_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableValueChangeListener)
	public static final Factory ITEM_SET_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableItemSetChangeListener)
	public static final Factory PROPERTY_SET_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggablePropertySetChangeListener)
	public static final Factory WINDOW_CLOSE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableWindowCloseListener)
	public static final Factory WINDOW_RESIZE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableWindowResizeListener)
	public static final Factory UPLOAD_FAILED_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableUploadFailedListener)
	public static final Factory UPLOAD_FINISHED_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableUploadFinishedListener)
	public static final Factory UPLOAD_STARTED_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableUploadStartedListener)
	public static final Factory UPLOAD_SUCCEEDED_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableUploadSucceededListener)
	public static final Factory TREE_COLLAPSE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTreeCollapseListener)
	public static final Factory TREE_EXPAND_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTreeExpandListener)
	public static final Factory ITEM_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableItemClickListener)
	public static final Factory SELECTED_TAB_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableSelectedTabChangeListener)
	public static final Factory TABLE_COLUMN_REORDER_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTableColumnReorderListener)
	public static final Factory TABLE_COLUMN_RESIZE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTableColumnResizeListener)
	public static final Factory TABLE_FOOTER_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTableFooterClickListener)
	public static final Factory TABLE_HEADER_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTableHeaderClickListener)
	public static final Factory POPUP_VISIBILITY_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggablePopupVisibilityListener)
	public static final Factory MOUSE_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableMouseClickListener)
	public static final Factory LOGIN_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableLoginListener)
	public static final Factory TEXT_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableTextChangeListener)
	public static final Factory SPLITTER_CLICK_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableSplitterClickListener)
	public static final Factory COMPONENT_ATTACH_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableComponentAttachListener)
	public static final Factory COMPONENT_DETACH_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableComponentDetachListener)
	public static final Factory REPAINT_REQUEST_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableRepaintRequestListener)
	public static final Factory COMPONENT_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableComponentListener)
	public static final Factory FRAGMENT_CHANGE_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableFragmentChangedListener)
	public static final Factory UPLOAD_PROGRESS_LISTENER_FACTORY = new ListenerFactory(PluggableListeners.PluggableUploadProgressListener)

	VaadinBuilder(boolean init = true) {
		super(init)
	}

	void registerFields() {
		registerFactory('button', BUTTON_FACTORY)
		registerFactory('nativeButton', NATIVE_BUTTON_FACTORY)
		registerFactory('checkBox', CHECK_BOX_FACTORY)
		registerFactory('dateField', DATE_FIELD_FACTORY)
		registerFactory('popupDateField', POPUP_DATE_FIELD_FACTORY)
		registerFactory('inlineDateField', INLINE_DATE_FIELD_FACTORY)
		registerFactory('richTextArea', RICH_TEXT_AREA_FACTORY)
		registerFactory('textField', TEXT_FIELD_FACTORY)
		registerFactory('textArea', TEXT_AREA_FACTORY)
		registerFactory('passwordField', PASSWORD_FIELD_FACTORY)
		registerFactory('progressIndicator', PROGRESS_INDICATOR_FACTORY)
		registerFactory('slider', SLIDER_FACTORY)

		registerFactory('select', SELECT_FACTORY)
		registerFactory('comboBox', COMBO_BOX_FACTORY)
		registerFactory('listSelect', LIST_SELECT_FACTORY)
		registerFactory('nativeSelect', NATIVE_SELECT_FACTORY)
		registerFactory('twinColSelect', TWIN_COL_SELECT_FACTORY)
		registerFactory('optionGroup', OPTION_GROUP_FACTORY)
		registerFactory('tree', TREE_FACTORY)
		registerFactory('treeTable', TREE_TABLE_FACTORY)
	}

	void registerLayouts() {
		registerFactory('absoluteLayout', ABSOLUTE_LAYOUT_FACTORY)
		registerFactory('gridLayout', GRID_LAYOUT_FACTORY)
		registerFactory('horizontalSplitPanel', HORIZONTAL_SPLIT_PANEL_FACTORY)
		registerFactory('verticalSplitPanel', VERTICAL_SPLIT_PANEL_FACTORY)
		registerFactory('customLayout', CUSTOM_LAYOUT_FACTORY)
		registerFactory('formLayout', FORM_LAYOUT_FACTORY)
		registerFactory('horizontalLayout', HORIZONTAL_LAYOUT_FACTORY)
		registerFactory('verticalLayout', VERTICAL_LAYOUT_FACTORY)
		registerFactory('cssLayout', CSS_LAYOUT_FACTORY)
	}

	void registerContainers() {
		registerFactory('panel', PANEL_FACTORY)
		registerFactory('tabSheet', TAB_SHEET_FACTORY)
		registerFactory('tab', TAB_FACTORY)
		registerFactory('accordion', ACCORDION_FACTORY)
		registerFactory('popupView', POPUP_VIEW_FACTORY)
		registerFactory('loginForm', LOGIN_FORM_FACTORY)
		registerFactory('customComponent', CUSTOM_COMPONENT_FACTORY)
		registerFactory('dragAndDropWrapper', DRAG_AND_DROP_WRAPPER_FACTORY)

		registerFactory('window', WINDOW_FACTORY)
	}

	void registerOthers() {
		registerFactory('embedded', EMBEDDED_FACTORY)
		registerFactory('link', LINK_FACTORY)
		registerFactory('label', LABEL_FACTORY)
		registerFactory('menuBar', MENU_BAR_FACTORY)
		registerFactory('upload', UPLOAD_FACTORY)

		registerFactory('audio', AUDIO_FACTORY)
		registerFactory('video', VIDEO_FACTORY)

		registerFactory('component', COMPONENT_FACTORY)
	}

	void registerListeners() {
		registerFactory('buttonClick', BUTTON_CLICK_LISTENER_FACTORY)
		registerFactory('layoutClick', LAYOUT_CLICK_LISTENER_FACTORY)
		registerFactory('blur', BLUR_LISTENER_FACTORY)
		registerFactory('focus', FOCUS_LISTENER_FACTORY)
		registerFactory('readOnlyStatusChange', READ_ONLY_STATUS_CHANGE_LISTENER_FACTORY)
		registerFactory('valueChange', VALUE_CHANGE_LISTENER_FACTORY)
		registerFactory('containerItemSetChange', ITEM_SET_CHANGE_LISTENER_FACTORY)
		registerFactory('containerPropertySetChange', PROPERTY_SET_CHANGE_LISTENER_FACTORY)
		registerFactory('windowClose', WINDOW_CLOSE_LISTENER_FACTORY)
		registerFactory('windowResized', WINDOW_RESIZE_LISTENER_FACTORY)
		registerFactory('uploadFailed', UPLOAD_FAILED_LISTENER_FACTORY)
		registerFactory('uploadFinished', UPLOAD_FINISHED_LISTENER_FACTORY)
		registerFactory('uploadStarted', UPLOAD_STARTED_LISTENER_FACTORY)
		registerFactory('uploadSucceeded', UPLOAD_SUCCEEDED_LISTENER_FACTORY)
		registerFactory('nodeCollapse', TREE_COLLAPSE_LISTENER_FACTORY)
		registerFactory('nodeExpand', TREE_EXPAND_LISTENER_FACTORY)
		registerFactory('itemClick', ITEM_CLICK_LISTENER_FACTORY)
		registerFactory('selectedTabChange', SELECTED_TAB_CHANGE_LISTENER_FACTORY)
		registerFactory('columnReorder', TABLE_COLUMN_REORDER_LISTENER_FACTORY)
		registerFactory('columnResize', TABLE_COLUMN_RESIZE_LISTENER_FACTORY)
		registerFactory('footerClick', TABLE_FOOTER_CLICK_LISTENER_FACTORY)
		registerFactory('headerClick', TABLE_HEADER_CLICK_LISTENER_FACTORY)
		registerFactory('popupVisibilityChange', POPUP_VISIBILITY_LISTENER_FACTORY)
		registerFactory('click', MOUSE_CLICK_LISTENER_FACTORY)
		registerFactory('onLogin', LOGIN_LISTENER_FACTORY)
		registerFactory('textChange', TEXT_CHANGE_LISTENER_FACTORY)
		registerFactory('splitterClick', SPLITTER_CLICK_LISTENER_FACTORY)
		registerFactory('componentAttachedToContainer', COMPONENT_ATTACH_LISTENER_FACTORY)
		registerFactory('componentDetachedFromContainer', COMPONENT_DETACH_LISTENER_FACTORY)
		registerFactory('repaintRequested', REPAINT_REQUEST_LISTENER_FACTORY)
		registerFactory('componentEvent', COMPONENT_LISTENER_FACTORY)
		registerFactory('fragmentChanged', FRAGMENT_CHANGE_LISTENER_FACTORY)
		registerFactory('updateProgress', UPLOAD_PROGRESS_LISTENER_FACTORY)
	}

	void registerFormNodes() {
		registerFactory('form', FORM_FACTORY)
		//registerFactory('field', FORM_FIELD_FACTORY)
	}

	void registerTableNodes() {
		registerFactory('table', TABLE_FACTORY)
		registerFactory('column', COLUMN_FACTORY)
	}

	void registerDataNodes() {
		registerFactory('item', ITEM_FACTORY)
	}
}
