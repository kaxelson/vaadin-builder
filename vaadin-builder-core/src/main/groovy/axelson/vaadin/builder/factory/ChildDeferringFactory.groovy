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

@Slf4j
class ChildDeferringFactory extends FamilyFactory {
	protected Map nodeChildren = [:]

	ChildDeferringFactory(Class klass) {
		super(klass)
	}

	@Override
	public void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
		addNodeChild(parent, child)
		super.setChild(builder, parent, child)
	}

	@Override
	public void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
		processNodeChildren(builder, parent, node, getNodeChildren(node))
		clearNodeChildren(node)
		super.onNodeCompleted(builder, parent, node)
	}

	public void processNodeChildren(FactoryBuilderSupport builder, Object parent, Object node, List children) {
		// default implementation does nothing
	}

	protected void addNodeChild(Object node, Object child) {
		nodeChildren[getNodeIdentifier(node)] = (nodeChildren[getNodeIdentifier(node)] ?: []) << child
	}

	protected List getNodeChildren(Object node) {
		nodeChildren[getNodeIdentifier(node)] ?: []
	}

	protected List clearNodeChildren(Object node) {
		nodeChildren.remove(getNodeIdentifier(node))
	}

	protected Object getNodeIdentifier(Object node) {
		System.identityHashCode(node)
	}
}
