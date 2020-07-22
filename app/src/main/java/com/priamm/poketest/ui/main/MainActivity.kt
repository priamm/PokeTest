package com.priamm.poketest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.priamm.poketest.R
import com.priamm.poketest.ui.base.DataBindingActivity
import com.priamm.poketest.databinding.ActivityMainBinding
import com.priamm.poketest.ui.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

  private val binding: ActivityMainBinding by binding(R.layout.activity_main)
  @VisibleForTesting val viewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.apply {
      lifecycleOwner = this@MainActivity
      adapter = PokemonAdapter()
      vm = viewModel.apply { fetchPokemonList(0) }
    }
  }
}
