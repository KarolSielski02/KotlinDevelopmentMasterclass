package pl.junkers.kotlinabasics

fun main() {

    val months = listOf("January", "February", "March")
    val anyTypes = listOf(1,2,3,true,false,"String")
    println(anyTypes.size)

    println(months.indexOf("March"))
    for (month in months){
        println(month)
    }

    val additionalMonths = months.toMutableList()
    val newMonths = arrayOf("April", "May", "June")
    additionalMonths.addAll(newMonths)
    println(additionalMonths)

    val days = mutableListOf<String>("Mon", "Tue", "Wed")
    days.add("Thu")
    days[2] = "Sunday"
    days.removeAt(days.indexOf("Sunday"))
    days.removeAll(days)
    print(days)
}
