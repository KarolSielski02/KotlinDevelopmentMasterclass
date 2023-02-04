package pl.junkers.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (tvInput?.text.toString().isEmpty() && (view as Button).text == "-") {
                tvInput?.append("-")
            } else if (tvInput?.text.toString().last().isDigit()
                && !isOperatorAdded(it.toString())
                && tvInput?.text.toString() != ""
            ) {
                tvInput?.append((view as Button).text)
            } else {
                return
            }
        }
    }

    fun onEqual(view: View) {
        if (tvInput?.text.toString().last().isDigit()) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    val one = splitValue[0]
                    val two = splitValue[1]
                    var oneAsNumber = one.toDouble()
                    if (prefix == "-") {
                        oneAsNumber *= -1
                    }
                    tvInput?.text = (oneAsNumber - two.toDouble()).toString()
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    val one = splitValue[0]
                    val two = splitValue[1]
                    var oneAsNumber = one.toDouble()
                    if (prefix == "-") {
                        oneAsNumber *= -1
                    }
                    tvInput?.text = (oneAsNumber + two.toDouble()).toString()
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    val one = splitValue[0]
                    val two = splitValue[1]
                    var oneAsNumber = one.toDouble()
                    if (prefix == "-") {
                        oneAsNumber *= -1
                    }
                    tvInput?.text = (oneAsNumber * two.toDouble()).toString()
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    val one = splitValue[0]
                    val two = splitValue[1]
                    var oneAsNumber = one.toDouble()
                    if (prefix == "-") {
                        oneAsNumber *= -1
                    }
                    tvInput?.text = (oneAsNumber / two.toDouble()).toString()
                }
            } catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }


        }

    }

    fun onDecimalPoint(view: View) {
        println("Hello Jello")
        println("here i am and here is the string ${tvInput?.text.toString()}")
        if (tvInput?.text.toString() != ""
            && !(tvInput?.text.toString().endsWith("."))
            && tvInput?.text.toString().last().isDigit()
            && !(tvInput?.text.toString().contains("."))
        ) {
            println("here i am and here is the string ${tvInput?.text.toString()}")
            tvInput?.append(".")
        }

    }
}