package cl.ing.puc.pokedemo.model

data class Pokemon(
    var id: Int,
    var name: String,
    var height: Double,
    var width: Double,
    var sprite: Sprite)

data class Sprite(
    var backFemale: String,
    var backShinyFemale: String,
    var backDefault: String,
    var frontFemale: String,
    var frontShinyFemale: String,
    var backShiny: String,
    var frontDefault: String,
    var frontShiny: String
)