import kotlin.random.Random

interface Observer {
    fun update(generator: NumberGenerator)
}

abstract class NumberGenerator {
    private val observers: ArrayList<Observer> = ArrayList()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun deleteObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        observers.forEach { it.update(this) }
    }

    abstract fun getNumber(): Int

    abstract fun execute()
}

class RandomNumberGenerator : NumberGenerator() {
    private var number: Int = 0

    override fun getNumber(): Int = number

    override fun execute() {
        repeat(20) {
            number = Random.nextInt(50)
            notifyObservers()
        }
    }
}

class DigitObserver : Observer {
    override fun update(generator: NumberGenerator) {
        println("DigitObserver: ${generator.getNumber()}")
    }
}

class GraphObserver : Observer {
    override fun update(generator: NumberGenerator) {
        val count = generator.getNumber()
        print("GraphObserver　")
        repeat(count) { print("*") }
        println("")
    }
}


fun main() {
    println("Hello, world!!!")
    val generator = RandomNumberGenerator()
    val observer1 = DigitObserver()
    val observer2 = GraphObserver()
    generator.addObserver(observer1)
    generator.addObserver(observer2)
    generator.execute()
}
