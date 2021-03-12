package io.yoondev.firstapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.yoondev.firstapp.databinding.ActivityMain2Binding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// Reactive eXtension
//  => 비동기 연산을 컬렉션의 다루는 것처럼 일반적인 방법을 통해 해결하고 싶다.

// Collection을 설계하는 기술
//  => Iterator Pattern
//   의도: 컬렉션의 내부 구조에 상관없이 요소를 열거하는 객체

//  Iterator(반복자)
//  Iterable
// => Collection 설계자는 제공해야 한다.


class SListIterator(private var current: SList.Node?) : Iterator<Int> {
    override fun hasNext(): Boolean = current != null

    // 현재의 current의 값을 반환하고, 다음 위치로 이동한다.
    override fun next(): Int {
        val ret = current?.value
        current = current?.next

        return ret!!
    }
}


class SList : Iterable<Int> {
    class Node(val value: Int, val next: Node?)

    var head: Node? = null

    val front: Int?
        get() = head?.value

    fun addFront(value: Int) {
        head = Node(value, head)
    }

    override fun iterator(): Iterator<Int> = SListIterator(head)
}


// Collection
// -   Iterable / Iterator : pull


// Rx
// - Observable / Observer : push

// Rx 구성 요소
//  1. Observable
//    : 이벤트를 발생하는 주체로, 이벤트 스트림을 통해 이벤트를 Observer 에게 전달합니다.

//  2. Observer
//    : Observable에서 발생한 이벤트에 반응하는 객체
//      이벤트가 발생하였을 때, 수행할 작업을 정의합니다.

//     "Observer가 Observable을 구독(subscribe)한다" 라고 합니다.
//       - onNext: 이벤트가 발생하였을 때
//       - onError: 오류가 발생하였을 때
//       - onComplete: 이벤트 스트림이 종료되었을 때

//  3. Scheduler
//    : 작업을 수행할 스레드를 지정할 수 있다.
//    UI 스레드 / IO 스레드 / 작업 스레드 ..

//  4. Operator
//    : 연산자는 이벤트 스트림을 통해 전달되는 이벤트를 처리할 수 있습니다.
//    => map / filter ...

//  5. Disposable
//    : Observer가 Observable을 구독할 때, '이벤트 스트림'이 생성됩니다.
//      반드시 해지해 주어야 합니다.
//   => dispose()


class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {

            /*
            githubApiRx.getUser("JakeWharton")
                .subscribe({
                    // onNext
                }, {
                    // onError
                }, {
                    // onComplete
                })
            */
            // RxKotlin - RxJava를 Kotlin에서 사용하기 편하도록 만들어진 라이브러리
            githubApiRx.getUser("JakeWharton")
                .observeOn(AndroidSchedulers.mainThread())  // RxAndroid
                .subscribeBy(
                    // ----Observer----
                    onNext = { user ->
                        Log.e("XXX", "onNext: $user")

                        updateUserUI(user)
                    },
                    onError = { error ->
                        Log.e("XXX", "onError: ${error.localizedMessage}")
                    },
                    onComplete = {
                        Log.e("XXX", "onComplete")
                    }
                    // ----Observer----
                )

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


}

/*
class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            val list = SList()
            list.addFront(10)
            list.addFront(20)
            list.addFront(30)
            // val list = listOf(10, 20, 30)

            // Log.e("XXX", "${list.front}")

            // 1. Iterator 이용하는 방법
            val iterator = list.iterator()
            while (iterator.hasNext()) {
                Log.e("XXX", "${iterator.next()}")
            }

            // 2. for-in(foreach / 향상된 for)
            for (e in list) {
                Log.e("XXX", "$e")
            }

            // 3. Iterator / Iterable
            //  => Collection을 다루는 일반적인 연산
            //     forEach / map / filter / ...
            list.forEach { e ->
                Log.e("XXX", "$e")
            }


        }

    }

}
*/

// Rx 적용하기
// : Call<E> -> Observable<E>
// => Retrofit 에서는 Call Adapter를 추가해야 합니다.

interface GithubApiRx {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<User>

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Observable<SearchUserResponse>
}

private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(httpClient)

    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()

    addConverterFactory(GsonConverterFactory.create(gson))
    addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Call<E> -> Observable<E>

}.build()

val githubApiRx: GithubApiRx = retrofit.create(GithubApiRx::class.java)