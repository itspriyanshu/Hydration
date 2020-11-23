package com.example.demo.Models

class CustomResponse {
    var userId: Float = 0.0f
    var id: Float = 0.0f
    var title: String? = null
    var body: String? = null

    override fun toString(): String {
        return "UserId is : "+userId+", ID is : "+id+", Title is : "+title+", Body is : "+body
    }
}