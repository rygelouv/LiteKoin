package com.github.rygelouv.litekoin

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Created by rygelouv on 7/10/20.
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


class Module {

    val declarationRegistry: MutableMap<KClass<*>, Declaration<Any>> = ConcurrentHashMap()

    inline fun <reified T: Any> factory(noinline declaration: Declaration<T>) {
        declarationRegistry[T::class] = declaration
    }


    inline fun <reified T: Any> get(): T {
        val declaration = declarationRegistry[T::class]
        var instance = declaration?.invoke()
        if (instance == null) {
            val liteKoin = LiteKoinContext.getLiteKoin()
            instance = liteKoin.declarations[T::class]?.invoke() ?: error("Unable to find declaration of type ${T::class.qualifiedName}")
        }
        return instance as T
    }


    operator fun plus(module: Module) = listOf(module, this)
}


operator fun List<Module>.plus(module: Module) = this + listOf(module)


val List<Module>.declarationRegistry: Map<KClass<*>, Declaration<Any>>
    get() = this.fold(this[0].declarationRegistry) { acc, module -> (acc + module.declarationRegistry) as MutableMap<KClass<*>, Declaration<Any>> }


