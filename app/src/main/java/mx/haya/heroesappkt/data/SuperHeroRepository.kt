package mx.haya.heroesappkt.data

import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.data.network.SuperHeroService
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    // Se inyecta el servicio
    private val service: SuperHeroService
) {

    suspend fun getSuperHero(id: String): SuperHeroModel? {
        return service.getSuperHero(id)
    }

}