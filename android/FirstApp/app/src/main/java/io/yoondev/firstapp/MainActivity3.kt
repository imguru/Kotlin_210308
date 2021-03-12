package io.yoondev.firstapp

import android.os.Bundle
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
private val httpClient: OkHttpClient = OkHttpClient.Builder().apply {

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

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
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
        }
    }
}