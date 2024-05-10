package com.example.pokemonapp.data.models.response.item

import com.google.gson.annotations.SerializedName


data class PokemonItemResponse(

    @SerializedName("abilities") var abilities: List<Abilities>? = null,
    @SerializedName("stats") var stats: List<Stats>? = null,
    @SerializedName("name") var name: String? = null,

//    @SerializedName("base_experience") var baseExperience: Int? = null,
//    @SerializedName("cries") var cries: Cries? = Cries(),
//    @SerializedName("forms") var forms: ArrayList<Forms> = arrayListOf(),
//    @SerializedName("game_indices") var gameIndices: ArrayList<GameIndices> = arrayListOf(),
//    @SerializedName("height") var height: Int? = null,
//    @SerializedName("held_items") var heldItems: ArrayList<String> = arrayListOf(),
//    @SerializedName("id") var id: Int? = null,
//    @SerializedName("is_default") var isDefault: Boolean? = null,
//    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
//    @SerializedName("moves") var moves: ArrayList<Moves> = arrayListOf(),
//    @SerializedName("order") var order: Int? = null,
//    @SerializedName("past_abilities") var pastAbilities: ArrayList<String> = arrayListOf(),
//    @SerializedName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
//    @SerializedName("species") var species: Species? = Species(),
//    @SerializedName("sprites") var sprites: Sprites? = Sprites(),
//    @SerializedName("types") var types: ArrayList<Types> = arrayListOf(),
//    @SerializedName("weight") var weight: Int? = null

)