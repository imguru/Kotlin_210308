package io.yoondev.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.yoondev.firstapp.databinding.ActivityMainBinding


// import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // activity_main.xml
    lateinit var binding: ActivityMainBinding

    // - X
    // android:id="@+id/btnHello"
    // andorid:id="@+id/button_hello"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setContentView(R.layout.activity_main)

        /*
        val button = findViewById<Button>(R.id.helloButton)
        button.setOnClickListener {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        }
        */

        // kotlin-android-extensions - 2020.11 deprecated!
        // plugins {
        //    // ...
        //    id 'kotlin-android-extensions'
        // }
        /*
        helloButton.setOnClickListener {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        }
        */

        // View binding / Data binding
        // - 권장
        // android:id="@+id/helloButton"
        // android:id="@+id/hello_button"
        binding.helloButton.setOnClickListener {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        }
    }
}