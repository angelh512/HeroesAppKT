package mx.haya.heroesappkt.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.domain.GetSuperHeroBatchUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSuperHeroBatchUseCase: GetSuperHeroBatchUseCase
) : ViewModel() {

    private var items = ArrayList<SuperHeroModel?>(emptyList())

    private val _updated = MutableLiveData<Boolean>()
    val updated: LiveData<Boolean> = _updated

    private var isFirstLoad = true

    fun loadSuperHeroes() {

        viewModelScope.launch {

            val methodName = "loadSuperHeroes > viewModelScope.launch"

            val startIndex = if (items.size == 0) 0 else items.get(items.size - 1)!!.id.toInt()

            Log.e(methodName, "startIndex: " + startIndex)

            items = getSuperHeroBatchUseCase.invoke(startIndex)

            if (isFirstLoad){
                _updated.postValue(false)
                isFirstLoad = false
            } else {
                _updated.postValue(true)
            }

        }
    }

    fun getSuperHeroes():ArrayList<SuperHeroModel?>{
        return items
    }

}
