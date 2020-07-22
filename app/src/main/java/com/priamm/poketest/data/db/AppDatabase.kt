package com.priamm.poketest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.priamm.poketest.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
  abstract fun pokemonDao(): PokemonDao
}
