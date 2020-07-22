package com.priamm.poketest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.priamm.poketest.R
import com.priamm.poketest.databinding.ItemPokemonBinding
import com.priamm.poketest.model.Pokemon

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

  private val items: MutableList<Pokemon> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<ItemPokemonBinding>(inflater, R.layout.item_pokemon, parent, false)
    return PokemonViewHolder(binding)
  }

  fun addPokemonList(pokemonList: List<Pokemon>) {
    items.addAll(pokemonList)
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
    val item = items[position]
    holder.binding.apply {
      pokemon = item
      executePendingBindings()

    }
  }

  override fun getItemCount() = items.size

  class PokemonViewHolder(val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root)
}
