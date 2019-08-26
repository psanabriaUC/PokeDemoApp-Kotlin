package cl.ing.puc.pokedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.ing.puc.pokedemo.model.Pokemon
import com.bumptech.glide.Glide

class PokemonDetailsActivity : AppCompatActivity() {
    companion object {
        const val POKEMON_DATA = "POKEMON_DATA"

        fun getIntent(context: Context, pokemon: Pokemon): Intent
        {
            val intent = Intent(context, PokemonDetailsActivity::class.java)

            intent.putExtra(POKEMON_DATA, pokemon)

            return intent
        }
    }

    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemonSpriteImageView : ImageView = findViewById(R.id.pokemon_sprite_image_view)
        val pokemonNameTextView : TextView =  findViewById(R.id.pokemon_name_text_view)
        val pokemonHeightTextView : TextView = findViewById(R.id.pokemon_height_view)
        val pokemonWeightTextView : TextView = findViewById(R.id.pokemon_weight_view)

        pokemon = savedInstanceState?.getSerializable(POKEMON_DATA) as Pokemon? ?: intent.getSerializableExtra(
            POKEMON_DATA) as Pokemon

        pokemonNameTextView.text = pokemon.name
        pokemonHeightTextView.text = pokemon.height.toString()
        pokemonWeightTextView.text = pokemon.weight.toString()
        Glide.with(this).load(pokemon.sprite.frontDefault).into(pokemonSpriteImageView)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putSerializable(POKEMON_DATA, pokemon)
        super.onSaveInstanceState(outState, outPersistentState)
    }

}
