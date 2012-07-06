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

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.vaadin.ui.Layout.MarginHandler
import com.vaadin.ui.Layout.MarginInfo

class LayoutFactory extends ComponentContainerFactory {
	private static final Logger logger = LoggerFactory.getLogger(LayoutFactory)
	
	LayoutFactory(Class klass) {
		super(klass)
	}
	
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		attributes.remove('margin')?.with {margin ->
			if (node instanceof MarginHandler) {
				if (margin instanceof String || margin instanceof Boolean) {
					node.margin = new MarginInfo(margin as boolean)
				} else if (margin instanceof MarginInfo) {
					node.margin = margin
				} else {
					logger.warn 'margin ignored, type must be String, Boolean, or MarginInfo'
				}
			} else {
				logger.warn 'margin ignored, node must be a MarginHandler'
			}
		}
		super.onHandleNodeAttributes(builder, node, attributes)
	}
}
