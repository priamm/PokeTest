package com.priamm.poketest.di

import com.priamm.poketest.data.network.HttpRequestInterceptor
import com.priamm.poketest.data.network.PokemonClient
import com.priamm.poketest.data.network.PokemonService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun providePokedexService(retrofit: Retrofit): PokemonService {
    return retrofit.create(PokemonService::class.java)
  }

  @Provides
  @Singleton
  fun providePokedexClient(pokedexService: PokemonService): PokemonClient {
    return PokemonClient(pokedexService)
  }
}
