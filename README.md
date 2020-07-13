# LiteKoin

LiteKoin is simplified version of the amazing DI framework Koin.

We tried to replicate the same thing but with a simple an naive approach.

## Overview

This is what you can do with it:

```kotlin
class UseCase(private val repo: Repository) {
    fun execute() = repo.getText()
}

class Repository {

    fun getText() = "Text from repository"

}


class ViewModel( private val useCase: UseCase) {

    fun showText() {
        print(useCase.execute())
    }
}


val mod1 = module {
    factory { ViewModel(get()) }
    factory { Repository() }
}

val mod2 = module {
    factory { UseCase(get()) }
}


fun main() {

    startLiteKoin {
        modules(mod1 + mod2)
    }

    val viewModel: ViewModel by inject()
    val repo: Repository = get()

    viewModel.showText()
}

```

For more details about how it's built checkout the Medium post [here:](https://proandroiddev.com/lets-build-our-own-simplified-version-of-koin-19a887306258)

### Licence

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```