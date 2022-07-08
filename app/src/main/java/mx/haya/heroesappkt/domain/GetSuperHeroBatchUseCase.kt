package mx.haya.heroesappkt.domain

import io.reactivex.rxjava3.core.Observable
import mx.haya.heroesappkt.data.SuperHeroRepository
import mx.haya.heroesappkt.data.model.SuperHeroModel
import javax.inject.Inject

class GetSuperHeroBatchUseCase @Inject constructor(
    private val repository: SuperHeroRepository
) {

    suspend operator fun invoke(startId: Int): ArrayList<SuperHeroModel?> {

        val items = ArrayList<SuperHeroModel?>()
        var currentId = startId + 1
        val endId = currentId + batchSize

        val observableList = ArrayList<Observable<SuperHeroModel>>()

        while (currentId < endId) {
            observableList.add(
                Observable.just(
                    repository.getSuperHero(currentId.toString())!!
                )
            )

            currentId++
        }

        Observable.concat(observableList).subscribe {
            items.add(it)
        }

        return items

    }

    companion object {
        private const val batchSize = 10
    }


}
