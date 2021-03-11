package io.yoondev.firstapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import io.yoondev.firstapp.databinding.ActivityMainBinding
import io.yoondev.firstapp.databinding.FragmentMainBinding
import io.yoondev.firstapp.databinding.ListFragmentBinding


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

        // fragment-ktx
        // - implementation 'androidx.fragment:fragment-ktx:1.3.1'
        /*
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, MainFragment())
            .commitNow()
        */

        supportFragmentManager.commitNow {
            // replace(R.id.mainFrame, MainFragment())
            replace(R.id.mainFrame, ListFragment())
        }

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

    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helloButton = view.findViewById<Button>(R.id.helloButton)
        helloButton.setOnClickListener {
            Toast.makeText(activity, "hello", Toast.LENGTH_SHORT).show()
        }
    }
    */

    // View binding - fragment_main.xml
    private var _binding: FragmentMainBinding? = null

    private val binding: FragmentMainBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.helloButton.setOnClickListener {
            Toast.makeText(activity, "hello", Toast.LENGTH_SHORT).show()
        }
    }

}


class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null

    private val binding: ListFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
























