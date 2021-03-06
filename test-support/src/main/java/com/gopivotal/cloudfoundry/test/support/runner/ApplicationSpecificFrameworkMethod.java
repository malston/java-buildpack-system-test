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

package com.gopivotal.cloudfoundry.test.support.runner;

import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

final class ApplicationSpecificFrameworkMethod extends FrameworkMethod {

    private final String application;

    ApplicationSpecificFrameworkMethod(String application, Method method) {
        super(method);
        this.application = application;
    }

    String getApplication() {
        return this.application;
    }

    @Override
    public String getName() {
        return String.format("%s#%s", super.getName(), this.application);
    }
}
