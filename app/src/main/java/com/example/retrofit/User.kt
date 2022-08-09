package com.example.retrofit

import com.google.gson.annotations.SerializedName

// DTO / POJO 모델 클래스 생성
data class User(
    @SerializedName("userId")
    val userId : Int,

    @SerializedName("id")
    val id : String,

    @SerializedName("body")
    val body : String

    // @SerializedName 으로 일치시켜 주지 않을 경우에 클래스 변수명이 일치해야함
    // @SerializeName() 로 변수명을 일치시켜주면 클래스 변수명이 달라도 알아서 매핑됨됨
)
