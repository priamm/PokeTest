package com.priamm.poketest.di

import android.app.Application
import androidx.room.Room
import com.priamm.poketest.data.db.AppDatabase
import com.priamm.poketest.data.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "Pokedex.db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

}
