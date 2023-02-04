package pl.junkers.kotlinabasics

fun main() {

    val arrayList: ArrayList<Double> = ArrayList<Double>()
    var num: Double = 0.0
    arrayList.add(5.64)
    arrayList.add(3.32)
    arrayList.add(43.32)
    arrayList.add(9.56)
    arrayList.add(8.23)
    for (number in arrayList) {
        num += number
    }
    println(num/arrayList.size)
}