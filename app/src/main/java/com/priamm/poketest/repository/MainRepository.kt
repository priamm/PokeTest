package com.priamm.poketest.repository

import com.priamm.poketest.data.network.PokemonClient
import com.priamm.poketest.data.db.PokemonDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository @Inject constructor(
  private val pokedexClient: PokemonClient,
  private val pokemonDao: PokemonDao
)  {

  suspend fun fetchPokemonList(
    page: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    var pokemons = pokemonDao.getPokemonList(page)
    if (pokemons.isEmpty()) {
      val response = pokedexClient.fetchPokemonList(page = page)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemons = response.results
          pokemons.forEach { pokemon -> pokemon.page = page }
          pokemonDao.insertPokemonList(pokemons)
          emit(pokemons)
          onSuccess()
        }
      }.onError {
        onError(message())
      }.onException {
        onError(message())
      }
    } else {
      emit(pokemons)
      onSuccess()
    }
  }.flowOn(Dispatchers.IO)
}
