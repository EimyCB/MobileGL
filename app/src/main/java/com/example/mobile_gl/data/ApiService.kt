package com.example.mobile_gl.data

import com.example.mobile_gl.data.model.MainResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun list(): Response<List<MainResponse>>?
}