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

package axelson.vaadin.builder.util

import groovy.util.logging.Slf4j
import axelson.vaadin.builder.VaadinBuilder

@Slf4j
class NewBuilderClosure extends Closure {
    Closure wrappedClosure
	VaadinBuilder builder
	long timesCalled

    NewBuilderClosure(VaadinBuilder builder, Closure wrappedClosure) {
		super(null)

		this.builder = builder

		// Make sure we don't reuse an old builder
		this.wrappedClosure = this.setBuilderRecursive(wrappedClosure, builder)
	}

    NewBuilderClosure(Closure wrappedClosure) {
		this(new VaadinBuilder(), wrappedClosure)
    }

    protected Object doCall(Object arguments) {
        def result = wrappedClosure.call(arguments)
		timesCalled++
		log.info "timesCalled: $timesCalled"
		return result
    }

    public Object getProperty(String property) {
		if (property == 'wrappedClosure') return getWrappedClosure()
		if (property == 'builder') return getBuilder()
		if (property == 'timesCalled') return getTimesCalled()
		return super.getProperty(property)
    }

    private void writeObject(ObjectOutputStream stream) {
        delegate = null
        wrappedClosure = wrappedClosure.dehydrate()
        stream.defaultWriteObject()
    }

    private void readObject(ObjectInputStream stream) {
        stream.defaultReadObject()
        delegate = null
        def vb = new VaadinBuilder()
        wrappedClosure = wrappedClosure.dehydrate().rehydrate(vb, vb, vb)
    }

	private Object setBuilderRecursive(Object o, VaadinBuilder builder) {
		if (o instanceof Closure) {
			Closure c = o
			return c.dehydrate().rehydrate(setBuilderRecursive(c.delegate, builder), setBuilderRecursive(c.owner, builder), setBuilderRecursive(c.thisObject, builder))
		} else if (o instanceof VaadinBuilder) {
			return builder
		} else {
			return o
		}
	}
}
