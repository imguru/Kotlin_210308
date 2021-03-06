package io.yoondev.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.yoondev.firstapp.databinding.ActivityMain2Binding
import okhttp3.*
import java.io.IOException

// Network API
//  - Java / Kotlin: OKHttp


// JSON Serialization / Deserialization
//  - Gson

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
// .dto.User // proguard 예외가 되어야 한다.

// DTO / DAO / VO => kotlin data class
//                   equals / hashCode / toString / copy
data class User(
    val login: String,
    val id: Int,
    // @field:SerializedName("avatar_url") val avatarUrl: String,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val nodeId: String,

    // 전달되지 않을 수 있는 값에 대해서는 Nullable이 좋습니다.
    val company: String?,
    val email: String?,
)

// 상수
// - 컴파일타입 상수: 메모리를 차지 하지 않는다.
// - 런타임 상수: 메모리를 할당하고, 변경할 수 없도록 한다.

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    // Java 상수
    // private static final String TAG = "MainActivity2"
    // private static final String TAG = MainActivity2.class.getName()

    companion object {
        const val TAG = "MainActivity2"
        // const val : 컴파일 타임 상수

        val TAG2 = MainActivity2::class.java.name
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // android.os.NetworkOnMainThreadException
        //  => Main Thread 안에서 Network 요청은 UI를 멈출 수 있다.
        //     호출을 허용하지 않습니다.

        binding.button.setOnClickListener {

            /*
            Thread(object: Runnable {
                override fun run() {

                }
            })
            */
            Thread {
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

                        // Can't toast on a thread that has not called Looper.prepare()
                        //  => 메인(UI) 스레드가 아닌 다른 스레드에서 UI 변경이 불가능합니다.

                        runOnUiThread {
                            // Toast.makeText(this, body.string(), Toast.LENGTH_SHORT).show()

                            val gson = GsonBuilder().apply {
                                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            }.create()

                            val json = body.string()

                            // val user = gson.fromJson(json, User::class.java)
                            val user = gson.fromJson<User>(json)

                            // Toast.makeText(this, "$user", Toast.LENGTH_SHORT).show()
                            // val email = user.email ?: "Null 값"
                            // Log.e(TAG, "$user $email")
                            /*
                            Glide.with(this)
                                .load(user.avatarUrl)
                                .circleCrop()
                                .into(binding.avatarImageView)
                            */

                            // Kotlin Image Library - coil
                            //  => Extension Function
                            /*
                            binding.avatarImageView.load(user.avatarUrl) {
                                // crossfade(true)
                                crossfade(3000)
                                transformations(CircleCropTransformation(), GrayscaleTransformation())
                            }

                            binding.nameTextView.text = user.name
                            binding.loginTextView.text = user.login
                            */
                            with(binding) {
                                avatarImageView.load(user.avatarUrl) {
                                    crossfade(3000)
                                    transformations(
                                        CircleCropTransformation(),
                                        GrayscaleTransformation(),
                                    )
                                }

                                nameTextView.text = user.name
                                loginTextView.text = user.login
                            }
                        }
                    }
                }

            }.start() // Thread

        }

    }
    */

    // 비동기 버전
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            val client = OkHttpClient.Builder().apply {
                // ...
            }.build()

            val request = Request.Builder().apply {
                url("https://api.github.com/users/JakeWharton")
                get()
            }.build()

            val call = client.newCall(request)
            call.enqueue(onFailure = { e ->
                runOnUiThread {
                    Toast.makeText(
                        this,
                        "Network error - ${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, onResponse = { response ->
                if (!response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "error - ${response.code}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@enqueue
                }

                val gson = GsonBuilder().apply {
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                }.create()

                val json = response.body?.string() ?: return@enqueue
                val user = gson.fromJson<User>(json)

                // UI Update
                runOnUiThread {
                    with(binding) {
                        avatarImageView.load(user.avatarUrl) {
                            crossfade(3000)
                            transformations(
                                CircleCropTransformation(),
                                GrayscaleTransformation(),
                            )
                        }

                        nameTextView.text = user.name
                        loginTextView.text = user.login
                    }
                }
            })

            // 비동기 => 다른 스레드에서 호출됩니다.
            /*
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity2,
                            "Network error - ${e.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity2,
                                "error - ${response.code}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        return
                    }

                    val gson = GsonBuilder().apply {
                        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    }.create()

                    val json = response.body?.string() ?: return
                    val user = gson.fromJson<User>(json)

                    // UI Update
                    runOnUiThread {
                        with(binding) {
                            avatarImageView.load(user.avatarUrl) {
                                crossfade(3000)
                                transformations(
                                    CircleCropTransformation(),
                                    GrayscaleTransformation(),
                                )
                            }

                            nameTextView.text = user.name
                            loginTextView.text = user.login
                        }
                    }
                }
            })
            */
        }
    }
}


//------
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}

// 인라인 함수 안에서 다른 함수에서 호출되는 함수를 인라인화 하기 위해서는 crossinline 키워드가 필요합니다.
inline fun Call.enqueue(
    crossinline onFailure: (e: IOException) -> Unit,
    crossinline onResponse: (response: Response) -> Unit
) {
    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) = onFailure(e)
        override fun onResponse(call: Call, response: Response) = onResponse(response)
    })
}






