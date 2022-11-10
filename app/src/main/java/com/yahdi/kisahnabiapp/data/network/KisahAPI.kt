package com.yahdi.kisahnabiapp.data.network

import com.yahdi.kisahnabiapp.data.model.KisahResponse
import retrofit2.Call
import retrofit2.http.GET

interface KisahAPI {
    @GET("kisahnabi")
    fun getAllKisah(): Call<List<KisahResponse>>
}