package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.ActivityMain2Binding
import io.yoondev.firstapp.databinding.ActivityMainBinding

// Network API
//  - Java / Kotlin: OKHttp


// Github API
//    https://api.github.com/users/JakeWharton
//    https://api.github.com/search/users?q=google

/*
{
  "login": "JakeWharton",
  "id": 66577,
  "avatar_url": "https://avatars.githubusercontent.com/u/66577?v=4",
  "type": "User",
  "name": "Jake Wharton",
  "company": "Square",
  "email": null,
}
*/


class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.button.setOnClickListener {





            
        }

    }
}













