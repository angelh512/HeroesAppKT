package mx.haya.heroesappkt.data.model

import com.google.gson.Gson

data class SuperHeroModel(
    val id: String,
    val name: String,
    val image: ImageModel,
    val powerstats: PowerstatsModel,
    val biography: BiographyModel,
    val appearance: AppearanceModel,
    val work: WorkModel,
    val connections: ConnectionModel
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
