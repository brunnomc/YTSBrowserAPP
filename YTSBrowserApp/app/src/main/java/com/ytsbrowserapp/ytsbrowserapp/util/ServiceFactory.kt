package com.ytsbrowserapp.ytsbrowserapp.util

import com.ytsbrowserapp.ytsbrowserapp.service.MovieService
import com.ytsbrowserapp.ytsbrowserapp.util.Constants.Companion.API_VERSION
import com.ytsbrowserapp.ytsbrowserapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceFactory {


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL + API_VERSION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun movieListService(): MovieService = retrofit.create(MovieService::class.java)
}