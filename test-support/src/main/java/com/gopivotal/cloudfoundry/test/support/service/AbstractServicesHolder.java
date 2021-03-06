/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gopivotal.cloudfoundry.test.support.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

abstract class AbstractServicesHolder implements ServicesHolder {

    private final Object monitor = new Object();

    private final ThreadLocal<Set<Service>> services = new ThreadLocal<Set<Service>>() {

        @Override
        protected Set<Service> initialValue() {
            return new HashSet<>();
        }

    };

    @Override
    public final void add(Service... services) {
        synchronized (this.monitor) {
            Collections.addAll(this.services.get(), services);
        }
    }

    @Override
    public final void clear() {
        synchronized (this.monitor) {
            this.services.get().clear();
        }
    }

    @Override
    public final Service[] get() {
        synchronized (this.monitor) {
            return this.services.get().toArray(new Service[this.services.get().size()]);
        }
    }

    @SuppressWarnings("unchecked")
    public final <T extends Service> T get(Class<T> klass) {
        for (Service service : this.services.get()) {
            if (klass.isAssignableFrom(service.getClass())) {
                return (T) service;
            }
        }

        throw new IllegalStateException(String.format("No %s service currently exists", klass.getName()));
    }

}
