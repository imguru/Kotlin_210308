package io.yoondev.firstapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.recyclerview.widget.RecyclerView
import io.yoondev.firstapp.databinding.ActivityMainBinding
import io.yoondev.firstapp.databinding.FragmentMainBinding
import io.yoondev.firstapp.databinding.ItemListBinding
import io.yoondev.firstapp.databinding.ListFragmentBinding
import kotlin.properties.Delegates


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


    var items = listOf(
        "Tom",
        "Bob",
        "Alice",
    )

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

        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter

        adapter.items = items

        binding.reloadButton.setOnClickListener {
            adapter.items = listOf(
                "A",
                "B",
                "C",
            )

            // adapter.notifyDataSetChanged()
        }

    }
}


private class ListAdapter : RecyclerView.Adapter<ListAdapter.Holder>() {
    // var items = emptyList<String>()
    var items: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class Holder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // item_list.xml
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {

        with(holder.binding) {
            textView.text = items[position]
            button.setOnClickListener {
                val context = holder.itemView.context
                Toast.makeText(context, items[position], Toast.LENGTH_SHORT).show()
            }

        }

        // with
        /*
        with(holder.itemView) {
            val textView = findViewById<TextView>(R.id.textView)
            val button = findViewById<Button>(R.id.button)

            textView.text = items[position]
            button.setOnClickListener {
                Toast.makeText(context, items[position], Toast.LENGTH_SHORT).show()
            }
        }
        */
    }

    override fun getItemCount(): Int = items.count()


}


// findViewById
/*
private class ListAdapter : RecyclerView.Adapter<ListAdapter.Holder>() {
    var items = emptyList<String>()

    class Holder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
    )


    override fun onBindViewHolder(holder: Holder, position: Int) {

        /*
        val textView = holder.itemView.findViewById<TextView>(R.id.textView)
        val button = holder.itemView.findViewById<Button>(R.id.button)

        textView.text = items[position]
        button.setOnClickListener {
            Toast.makeText(holder.itemView.context, items[position], Toast.LENGTH_SHORT).show()
        }
        */

        // with
        with(holder.itemView) {
            val textView = findViewById<TextView>(R.id.textView)
            val button = findViewById<Button>(R.id.button)

            textView.text = items[position]
            button.setOnClickListener {
                Toast.makeText(context, items[position], Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)
}
*/




















