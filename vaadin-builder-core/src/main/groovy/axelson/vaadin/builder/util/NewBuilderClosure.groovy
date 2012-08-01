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

import axelson.vaadin.builder.VaadinBuilder

class NewBuilderClosure extends Closure {
    Closure wrappedClosure

    NewBuilderClosure(Closure wrappedClosure) {
        super(null)
        def vb = new VaadinBuilder()
        this.wrappedClosure = wrappedClosure.dehydrate().rehydrate(vb, vb, vb)
    }

    protected Object doCall(Object arguments) {
        wrappedClosure.call(arguments)
    }

    public Object getProperty(String property) {
        property == 'wrappedClosure' ? getWrappedClosure() : super.getProperty(property)
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
}
