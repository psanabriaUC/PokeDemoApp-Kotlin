package cl.ing.puc.pokedemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import cl.ing.puc.pokedemo.model.Pokemon
import com.bumptech.glide.Glide

class PokemonAdapter(context: Context) : ArrayAdapter<Pokemon>(context, 0) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.pokemon_item_layout, parent, false)!!
        }

        val pokemonSpriteImageView : ImageView = view.findViewById(R.id.pokemon_sprite_image_view)
        val pokemonNameTextView : TextView = view.findViewById(R.id.pokemon_name_text_view)
        val pokemon = getItem(position)

        pokemonNameTextView.text = pokemon?.name
        Glide.with(context).load(pokemon?.sprite?.frontDefault).into(pokemonSpriteImageView)

        return view
    }
}