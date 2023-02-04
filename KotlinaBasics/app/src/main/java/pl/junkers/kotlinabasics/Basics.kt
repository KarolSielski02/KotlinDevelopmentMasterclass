package pl.junkers.kotlinabasics

import java.util.*

fun main() {
    var myCar = Car("A3", "Audi")
    var myECar = ElectricCar("S-Model", "Tesla", 85.0)

    myCar.drive(230.0)
    myECar.drive(430.0)
}

class Person(
    firstName: String,
    lastName: String
) {
    //Member Variables - Properties
    var age: Int? = null
    var firstName: String = firstName
    var lastName: String = lastName
    var hobby: String = "Watching Netflix"

    //Member Function - Methods
    init {
        println("Person Created")
    }

    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName) {
        this.age = age
        this.firstName = firstName
        this.lastName = lastName
        println("used 2nd constructor")
    }

    fun stateHobby() {
        println("$firstName's hobby is $hobby")
    }


}

data class User(val id: Long, val name: String)

open class Vehicle {

}

open class Car(val name: String, val brand: String) : Vehicle() {
    open var range: Double = 0.0

    fun extendRange(amount: Double) {
        if (amount > 0) {
            range += amount
        }
    }

    open fun drive(distance: Double) {
        println("Drove for $distance KM")
    }
}

class ElectricCar(name: String, brand: String, batteryLife: Double) :
    Car(name, brand) {

    override var range = batteryLife * 5
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }

    fun drive(){
        println("Drove for $range KM on electricity")
    }
}

interface Drivable{
    val maxSpeed: Double
    fun drive():String
    fun brake(){
        println("The drivable is breaking")
    }
}