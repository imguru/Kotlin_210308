package io.yoondev.firstapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.yoondev.firstapp.databinding.ActivityMain2Binding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// Retrofit
//  => OKHttpClient 를 이용할 때의 보일러플레이트를 제거할 수 있습니다.
//    1) Request
//    3) Response.body -> gson -> User
//      => converter factory

//    2) runOnUiThread

// Github API
//    https://api.github.com/users/JakeWharton
//    https://api.github.com/search/users?q=google&per_page=5

data class SearchUserResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<User>
)

//-------------
// 1. Api Interface 작성
interface GithubApi {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Call<SearchUserResponse>
}

// 2. OKHttpClient 객체 생성
val httpClient: OkHttpClient = OkHttpClient.Builder().apply {

}.build()

// 3. Retrofit 객체 생성
private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(httpClient)


    // Converter Factory
    //----
    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()

    addConverterFactory(GsonConverterFactory.create(gson))
    //----

}.build()

// 4. Retrofit 객체가 GithubApi 인터페이스의 어노테이션을 분석해서,
//    자동으로 객체를 생성한다. - Reflection
val githubApi: GithubApi = retrofit.create(GithubApi::class.java)
//-----------

// Call - execute(동기) / enqueue(비동기)

// - UI 스레드에서 요청할 경우, 'enqueue'를 사용하는 것이 좋습니다.
// - UI 스레드가 아닌 스레드에서 요청 할 경우, 'execute'를 사용하는 것이 좋습니다.

// => HTTP API(동기)
//   : 1. Thread 생성
//     2. API 요청
//     3. Handler -> UI Update 수행
//     => AsyncTask

//    Rx => 비동기 흐름을 효과적으로 제어하는 방법
//     : Rx(Reactive eXtension)

//    RxJava
//    - RxSwift / RxJs ...
class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.button.setOnClickListener {
            // 1. 검색 - "Jake" (비동기) - searchUser
            // 2. 검색 결과의 첫번째 사용자의 User 정보를 요청 (비동기) - getUser
            // 3. 사용자 정보 출력 - UI

            githubApi.searchUser("Jake")
                .enqueue(onResponse = { response ->

                    /*
                    response.body()?.let {
                        val items = it.items
                        items.firstOrNull()?.login?.let { login ->
                            githubApi.getUser(login)
                                .enqueue(
                                    onResponse = enqueue2@{ userResponse ->
                                        val user = userResponse.body() ?: return@enqueue2
                                        updateUserUI(user)
                                    },
                                    onFailure = { e ->
                                        Toast.makeText(
                                            this,
                                            e.localizedMessage,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                        }
                    }
                    */

                    val items = response.body()?.items ?: emptyList()
                    val login = items.firstOrNull()?.login ?: return@enqueue

                    githubApi.getUser(login)
                        .enqueue(
                            onResponse = enqueue2@{ userResponse ->
                                val user = userResponse.body() ?: return@enqueue2
                                updateUserUI(user)
                            },
                            onFailure = {
                                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                            }
                        )


                }, onFailure = {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                })


        }

    }

    private fun updateUserUI(user: User) {
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

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {

            /*
            githubApi.searchUser("Jake")
                .enqueue(object : Callback<SearchUserResponse> {
                    override fun onResponse(
                        call: Call<SearchUserResponse>,
                        response: Response<SearchUserResponse>
                    ) {
                        val body = response.body() ?: return
                        Log.e("XXX", "$body")
                    }

                    override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {

                    }
                })
             */
            githubApi.searchUser("Jake")
                .enqueue(
                    onResponse = { response ->
                        val body = response.body() ?: return@enqueue
                        Log.e("XXX", "$body")
                    },
                    onFailure = { t ->
                        Toast.makeText(this, "error - ${t.localizedMessage}", Toast.LENGTH_SHORT)
                            .show()
                    }
                )

            /*
            val call = githubApi.getUser("JakeWharton")
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (!response.isSuccessful) {
                        return
                    }

                    val user = response.body() ?: return

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

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity3,
                        "Failed - ${t.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            */
        }
    }
    */
}

//-------
inline fun <E> Call<E>.enqueue(
    crossinline onResponse: (response: Response<E>) -> Unit,
    crossinline onFailure: (t: Throwable) -> Unit
) {
    enqueue(object : Callback<E> {
        override fun onResponse(call: Call<E>, response: Response<E>) = onResponse(response)
        override fun onFailure(call: Call<E>, t: Throwable) = onFailure(t)
    })
}










