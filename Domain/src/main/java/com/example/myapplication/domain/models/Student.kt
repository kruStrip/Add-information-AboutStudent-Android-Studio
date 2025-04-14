package com.example.myapplication.domain.models

data class Student(
    val id: Int = 0,
    val profilePicture: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val age: Int
)