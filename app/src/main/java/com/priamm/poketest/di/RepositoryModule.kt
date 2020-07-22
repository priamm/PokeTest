package com.priamm.poketest.di

import com.priamm.poketest.data.db.PokemonDao
import com.priamm.poketest.data.network.PokemonClient
import com.priamm.poketest.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

  @Provides
  @ActivityRetainedScoped
  fun provideMainRepository(
          pokedexClient: PokemonClient,
          pokemonDao: PokemonDao
  ): MainRepository {
    return MainRepository(pokedexClient, pokemonDao)
  }

}
