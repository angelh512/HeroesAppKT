package mx.haya.heroesappkt.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/792522785087466/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}