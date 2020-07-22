package com.priamm.poketest.data.network

import javax.inject.Inject

class PokemonClient @Inject constructor(
  private val pokedexService: PokemonService
) {

  suspend fun fetchPokemonList(
    page: Int
  ) = pokedexService.fetchPokemonList(
    limit = PAGING_SIZE,
    offset = page * PAGING_SIZE
  )

  companion object {
    private const val PAGING_SIZE = 20
  }
}
