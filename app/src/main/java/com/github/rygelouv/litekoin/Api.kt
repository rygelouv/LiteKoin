package com.github.rygelouv.litekoin

/**
 * Created by rygelouv on 7/7/20.
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


fun getLiteKoin() = LiteKoinContext.getLiteKoin()


inline fun <reified T: Any> get(): T {
    val service = getLiteKoin().resolveInstance(T::class)
    return service.instance as T
}


inline fun <reified T: Any> inject(): Lazy<T> = lazy { get<T>() }


fun module(block: Module.() -> Unit) = Module().apply(block)

