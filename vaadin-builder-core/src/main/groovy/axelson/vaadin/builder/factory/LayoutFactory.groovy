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
import axelson.vaadin.builder.factory.ComponentFactory.ExpandRatioAttribute

import com.vaadin.ui.AbstractOrderedLayout
import com.vaadin.ui.Layout
import com.vaadin.ui.Layout.MarginHandler
import com.vaadin.ui.Layout.MarginInfo

@Slf4j
class LayoutFactory extends ComponentContainerFactory {
	LayoutFactory(Class klass) {
		super(klass)
	}

	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		attributes.remove('margin')?.with {margin ->
			applyMargin(node, margin)
		}
		super.onHandleNodeAttributes(builder, node, attributes)
	}

	protected void applyMargin(Object node, boolean margin) {
		if (node instanceof MarginHandler) {
			if (margin instanceof String || margin instanceof Boolean) {
				node.margin = new MarginInfo(margin as boolean)
			} else if (margin instanceof MarginInfo) {
				node.margin = margin
			} else {
				log.warn 'margin ignored, type must be String, Boolean, or MarginInfo'
			}
		} else {
			log.warn 'margin ignored, node must be a MarginHandler'
		}
	}

	@Override
	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		super.processNodeChildren(builder, parent, node, children)

		// expandRatio has to be set after component has been added to the container.
		// That's why we're doing this after the call to super.
		children.findAll{it instanceof ExpandRatioAttribute}.with {expandRatios ->
			processExpandRatios(node, expandRatios)
		}

		// alignment has to be set after component has been added to the container.
		// That's why we're doing this after the call to super.
		children.findAll{it instanceof AlignmentAttribute}.with {alignments ->
			processAlignments(node, alignments)
		}
	}

	protected void processExpandRatios(Object node, List expandRatios) {
		if (node instanceof AbstractOrderedLayout) {
			AbstractOrderedLayout aol = node
			expandRatios.each {ExpandRatioAttribute er ->
				aol.setExpandRatio(er.component, er.expandRatio)
			}
		}
	}

	protected void processAlignments(Object node, List alignments) {
		if (node instanceof Layout.AlignmentHandler) {
			Layout.AlignmentHandler ah = node
			alignments.each {AlignmentAttribute alignment ->
				ah.setComponentAlignment(alignment.component, alignment.alignment)
			}
		}
	}
}
