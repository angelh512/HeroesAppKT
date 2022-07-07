package mx.haya.heroesappkt.data.network

import mx.haya.heroesappkt.data.model.SuperHeroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroAPI {

    @GET("{id}")
    suspend fun getSuperHero(@Path("id") id: String): Response<SuperHeroModel>

}