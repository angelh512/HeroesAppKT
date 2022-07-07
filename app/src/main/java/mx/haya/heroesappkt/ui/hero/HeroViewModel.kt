package mx.haya.heroesappkt.ui.hero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.domain.GetSuperHeroUseCase
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getSuperHeroUseCase: GetSuperHeroUseCase
) : ViewModel() {

    private val _superHeroModel = MutableLiveData<SuperHeroModel>()
    val superHero: LiveData<SuperHeroModel> = _superHeroModel

    var id: String = "1"

    fun onCreate() {

        val TAG = "onCreate"

        viewModelScope.launch {

            val superHero = getSuperHeroUseCase.invoke(id)

            if (superHero != null) {
                _superHeroModel.value = superHero
                Log.d(TAG, superHero.toString())
            }
        }
    }

}