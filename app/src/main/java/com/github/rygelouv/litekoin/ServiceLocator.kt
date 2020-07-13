package com.github.rygelouv.litekoin

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Created by rygelouv on 7/8/20.
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


class ServiceLocator {

    private val serviceMap: MutableMap<KClass<*>, Service> = ConcurrentHashMap()

    fun <T : Any> getService(clz: KClass<T>): Service {
        return serviceMap[clz] ?: error("Unable to find definition of $clz")
    }

    private fun addService(service: Service) {
        serviceMap[service.type] = service
    }


    fun loadModules(modules: List<Module>) {
        modules.forEach(::registerModule)
    }

    private fun registerModule(module: Module) {
        module.declarationRegistry.forEach {
            addService(it.value.toService())
        }
    }

}


