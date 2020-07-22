package com.priamm.poketest.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.priamm.poketest.model.Pokemon
import com.priamm.poketest.ui.adapter.PokemonAdapter
import com.priamm.poketest.ui.main.MainViewModel
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}

@BindingAdapter("paginationPokemonList")
fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.isLoading.get() },
    loadMore = { viewModel.fetchPokemonList(it) },
    onLast = { false }
  ).run {
    threshold = 8
  }
}

@BindingAdapter("adapterPokemonList")
fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Pokemon>?) {
  pokemonList.whatIfNotNullOrEmpty {
    (view.adapter as? PokemonAdapter)?.addPokemonList(it)
  }
}
