package mx.haya.heroesappkt.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.util.RetrofitHelper
import retrofit2.Response
import javax.inject.Inject

class SuperHeroService @Inject constructor() {

    private val retrofit = RetrofitHelper.getRetrofit()

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getSuperHero(id: String): SuperHeroModel? {
        // Creamos la coroutine
        return withContext(dispatcher) {
            val response: Response<SuperHeroModel> = retrofit
                .create(SuperHeroAPI::class.java)
                .getSuperHero(id)

            response.body()
        }
    }

}









