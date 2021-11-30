package com.berke.internshipproject.network.remote

import com.berke.internshipproject.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?")
    suspend fun searchImage(
        @Query("q") q: String
    ): Response<ResponseModel>
}