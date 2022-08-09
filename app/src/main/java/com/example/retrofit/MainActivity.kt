package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG : String = "jeongmin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // 레트로핏 사용을 위한 함수
    private fun useRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)

        val tvCheck = findViewById<TextView>(R.id.tvCheck)

        service.getUserPage("1")?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    // 정상적으로 통신이 성공한 경우
                    val result : User?=response.body()
                    Log.d(TAG, "onResponse 성공 : ${result?.toString()}")
                    tvCheck.text = "onResponse 성공 : ${result?.toString()}"
                } else {
                    // 통신이 실패한 경우(응답코드, 3xx, 4xx 등)
                    Log.d(TAG, "onResponse 실패 ")
                    tvCheck.text = "onResponse 실패"
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                Log.d(TAG, "onFailure 에러 : ${t.message.toString()}")
                tvCheck.text = "onFailure 에러 : ${t.message.toString()}"
            }
        })
    }

    fun onClickCheck(view: View) {
        useRetrofit()
    }
}