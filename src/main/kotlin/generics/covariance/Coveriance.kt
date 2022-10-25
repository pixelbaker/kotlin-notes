package generics.covariance

/* Introduction */

//This class is declared as covariant on T
interface Producer<out T> {
    fun feed() {}
}


/* Example */

open class Animal {
    open fun feed() = println("Feed the animal")
}

//without the "out" the type parameter T isn't declared as covariant
//class Herd<T : Animal> {
//with the "out" the the T parameter is now covariant.
class Herd<out T : Animal> {
    val animals: List<T> = emptyList()
    val size: Int get() = animals.size
    operator fun get(i: Int): T {
        return animals[i]
    }
}

class Cat : Animal() {
    fun cleanLitter() = println("Tidy up the litter")
}


fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    feedAll(cats) //Error, when T isn't made covariant. Inferred type is Herd<Cat> but Herd<Animal> was expected
}
