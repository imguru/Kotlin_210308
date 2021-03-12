package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.ActivityMain2Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // android.os.NetworkOnMainThreadException
        //  => Main Thread 안에서 Network 요청은 UI를 멈출 수 있다.
        //     호출을 허용하지 않습니다.

        binding.button.setOnClickListener {

        }
    }
}