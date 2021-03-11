package io.yoondev.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.ActivityMain2Binding
import io.yoondev.firstapp.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request

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

            // 1. OKHttpClient 객체 생성 - Builder
            val client = OkHttpClient.Builder().apply {
                // ...
            }.build()

            // 2. Request 객체 생성
            //    REST API(HTTP) - GET /     POST / PUT / DELETE
            //                                  --> Body
            val request = Request.Builder().apply {
                url("https://api.github.com/users/JakeWharton")
                get()
                // method("GET", null)

            }.build()

            // 3. 'Call'을 생성한다.
            //    => 동기 / 비동기를 선택할 수 있습니다.
            val call = client.newCall(request)


            // 4. 동기로 호출한다.
            val response = call.execute()

            // HTTP status code
            // 200 ~ 299: Success
            // 400 ~ 499: Client Error
            // 500 ~ 599: Server Error

            // if (response.code in 200..299)
            if (response.isSuccessful) {
                response.body?.let { body ->
                    Toast.makeText(this, body.string(), Toast.LENGTH_SHORT).show()
                }
            }
            
        }

    }
}













