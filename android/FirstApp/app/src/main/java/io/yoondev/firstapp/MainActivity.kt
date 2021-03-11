package io.yoondev.firstapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
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
        // binding.helloButton.setOnClickListener {
        //    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        // }

        val pref = getSharedPreferences("firstapp", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("name", "Tom")
        editor.apply()

        // core-ktx : Android Kotlin Extension - KTX
        // - apply
        pref.edit {
            putString("name", "Tom")
        }

        // - commit
        pref.edit(commit = true) {
            putString("name", "Tom")
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, MainFragment())
            .commitNow()
    }
}


// MainFragment
class MainFragment : Fragment() {
    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

}























