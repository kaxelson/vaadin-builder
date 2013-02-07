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

package axelson.vaadin.builder.factory

import groovy.util.logging.Slf4j

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Panel

@Slf4j
class PanelFactory extends LayoutFactory {
	PanelFactory() {
		this(Panel)
	}

	protected PanelFactory(Class klass) {
		super(klass)
	}

	@Override
	protected void applySpacing(Object node, boolean spacing) {
		assert node instanceof Panel
		Panel panel = node
		super.applySpacing(panel.content, spacing)
	}

	@Override
	protected void applyMargin(Object node, Object margin) {
		assert node instanceof Panel
		Panel panel = node
		super.applyMargin(panel.content, margin)
	}

	@Override
	protected void processExpandRatios(Object node, List expandRatios) {
		assert node instanceof Panel
		Panel panel = node
		super.processExpandRatios(panel.content, expandRatios)
	}

	@Override
	protected void processAlignments(Object node, List alignments) {
		assert node instanceof Panel
		Panel panel = node
		super.processAlignments(panel.content, alignments)
	}
}
