# LiteKoin

LiteKoin is simplified version of the amazing DI framework Koin.

We tried to replicated the same thing but with a simple an naive approach.

This is what you can do with it:

```
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

For more details about how it's built checkout the Medium post [here:](https://martinfowler.com/eaaDev/PassiveScreen.html)