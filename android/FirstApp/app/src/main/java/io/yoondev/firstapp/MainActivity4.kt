package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.ActivityMain2Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}