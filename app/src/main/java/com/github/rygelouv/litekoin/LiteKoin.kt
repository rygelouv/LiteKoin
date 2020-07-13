package com.github.rygelouv.litekoin

import kotlin.reflect.KClass

/**
 * Created by rygelouv on 7/11/20.
 * <p>
 * LiteKoin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


class LiteKoin {

    private val registry = ServiceLocator()
    lateinit var declarations: Map<KClass<*>, Declaration<Any>>

    fun loadModules(modules: List<Module>) {
        declarations = modules.declarationRegistry
        println("Registry Size === ${modules.declarationRegistry.size}")
        registry.loadModules(modules)
    }

    fun resolveInstance(type: KClass<*>) = registry.getService(type)
}
