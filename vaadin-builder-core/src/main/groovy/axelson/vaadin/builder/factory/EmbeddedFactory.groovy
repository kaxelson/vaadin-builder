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

import com.vaadin.ui.Embedded

@Slf4j
class EmbeddedFactory extends ComponentFactory {
	EmbeddedFactory() {
		super(Embedded)
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
		attributes.remove('parameters')?.with {parameters ->
			if (node instanceof Embedded) {
				Embedded embedded = node
				assert parameters instanceof Map
				parameters.each {k, v ->
					embedded.setParameter(k, v)
				}
			} else {
				log.info 'Ignoring parameters attribute since node is not an instance of Embedded'
			}
		}
		super.onHandleNodeAttributes(builder, node, attributes)
	}
}
