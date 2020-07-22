package com.priamm.poketest.data.network

import com.priamm.poketest.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

  @GET("pokemon")
  suspend fun fetchPokemonList(
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0
  ): ApiResponse<PokemonResponse>

}
