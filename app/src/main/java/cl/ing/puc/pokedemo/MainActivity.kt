package cl.ing.puc.pokedemo

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.ing.puc.pokedemo.model.Pokemon
import cl.ing.puc.pokedemo.model.Sprite
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PokemonAdapter
    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.pokemon_list_view)
        val refreshButton: Button = findViewById(R.id.refresh_button)

        adapter = PokemonAdapter(this)
        queue = Volley.newRequestQueue(this)

        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                goToPokemon(
                    adapter.getItem(
                        position
                    )!!
                )
            }
        refreshButton.setOnClickListener { onRefresh() }
    }

    private fun onRefresh() {
        val url = "https://pokeapi.co/api/v2/pokemon"

        val listRequest = JsonObjectRequest(url, null, Response.Listener { response: JSONObject ->
            run {
                try {
                    val pokemonListJSON = response.getJSONArray("results")
                    adapter.clear()

                    for (i in 0 until pokemonListJSON.length()) {
                        retrievePokemon(pokemonListJSON.getJSONObject(i))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, Response.ErrorListener { error ->
            run {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }
        })
        queue.add(listRequest)
    }

    private fun retrievePokemon(pokemonData: JSONObject?) {
        val url = pokemonData?.getString("url")
        val pokemonRequest =
            JsonObjectRequest(url, null, Response.Listener { response: JSONObject ->
                parsePokemon(response)
            }, Response.ErrorListener { error ->
                run {
                    Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show()
                    error.printStackTrace()
                }
            })

        queue.add(pokemonRequest)
    }

    private fun parsePokemon(response: JSONObject) {
        val spriteJson = response.getJSONObject("sprites")
        val sprite = Sprite(
            spriteJson.getString("back_female"),
            spriteJson.getString("back_shiny_female"),
            spriteJson.getString("back_default"),
            spriteJson.getString("front_female"),
            spriteJson.getString("front_shiny_female"),
            spriteJson.getString("back_shiny"),
            spriteJson.getString("front_default"),
            spriteJson.getString("front_shiny")
        )

        val pokemon = Pokemon(
            response.getInt("id"),
            response.getString("name"),
            response.getDouble("height"),
            response.getDouble("weight"),
            sprite
        )

        adapter.add(pokemon)
    }

    private fun goToPokemon(pokemon: Pokemon) {
        val intent = PokemonDetailsActivity.getIntent(this, pokemon)

        startActivity(intent)
    }
}
