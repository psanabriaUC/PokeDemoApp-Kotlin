package cl.ing.puc.pokedemo.model

import java.io.Serializable

data class Pokemon(
    var id: Int,
    var name: String,
    var height: Double,
    var weight: Double,
    var sprite: Sprite) : Serializable

data class Sprite(
    var backFemale: String,
    var backShinyFemale: String,
    var backDefault: String,
    var frontFemale: String,
    var frontShinyFemale: String,
    var backShiny: String,
    var frontDefault: String,
    var frontShiny: String
) : Serializable