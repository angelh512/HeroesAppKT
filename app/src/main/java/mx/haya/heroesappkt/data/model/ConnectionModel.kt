package mx.haya.heroesappkt.data.model

import com.google.gson.annotations.SerializedName

data class ConnectionModel(
    @SerializedName("group-affiliation")
    val groupAffiliation: String,

    val relatives: String
)