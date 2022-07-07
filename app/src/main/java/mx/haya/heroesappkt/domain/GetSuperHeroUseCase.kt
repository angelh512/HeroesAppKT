package mx.haya.heroesappkt.domain

import mx.haya.heroesappkt.data.SuperHeroRepository
import mx.haya.heroesappkt.data.model.SuperHeroModel
import javax.inject.Inject

class GetSuperHeroUseCase @Inject constructor(
    private val repository: SuperHeroRepository
) {

    suspend operator fun invoke(id: String): SuperHeroModel? {
        return repository.getSuperHero(id)
    }

}