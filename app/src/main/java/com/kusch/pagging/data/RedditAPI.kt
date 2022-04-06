package com.kusch.pagging.data

import com.kusch.pagging.data.models.RedditResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val PAGE_SIZE = 10

interface MyApi {

    @GET("aww/hot.json")
    suspend fun getPosts(
        @Query("after") after: String,
        @Query("limit") size: Int = PAGE_SIZE
    ): RedditResponse

    companion object {
        private const val BASE_URL = "https://www.reddit.com/r/"

        operator fun invoke(): MyApi = Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build()).addConverterFactory(GsonConverterFactory.create())
            .build().create(MyApi::class.java)
    }
}

