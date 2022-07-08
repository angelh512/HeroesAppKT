package mx.haya.heroesappkt.ui.adapters

import mx.haya.heroesappkt.data.model.SuperHeroModel

interface RecyclerViewItemClickListener {
    fun onItemClickListener(data: SuperHeroModel)
}
