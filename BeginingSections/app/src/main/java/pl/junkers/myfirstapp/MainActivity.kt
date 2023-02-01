package pl.junkers.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClickMe = findViewById<Button>(R.id.btnMyButton)
        val tvMyTextView = findViewById<TextView>(R.id.textView)
        var peopleCount = 0
        btnClickMe.setOnClickListener {
            peopleCount++
            if (peopleCount == 1) {
                tvMyTextView.text = "There is: $peopleCount human!"
            } else{
                tvMyTextView.text = "There are: $peopleCount humans!"
            }
            //Toast.makeText(this, "Hey I'm a toast!", Toast.LENGTH_LONG).show()
        }
    }
}
