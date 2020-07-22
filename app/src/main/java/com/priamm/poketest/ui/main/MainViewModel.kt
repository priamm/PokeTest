package com.priamm.poketest.ui.main

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.priamm.poketest.ui.base.LiveCoroutinesViewModel
import com.priamm.poketest.model.Pokemon
import com.priamm.poketest.repository.MainRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
  private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

  private var pokemonFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
  val pokemonListLiveData: LiveData<List<Pokemon>>

  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val toastLiveData: MutableLiveData<String> = MutableLiveData()

  init {
    Timber.d("init MainViewModel")

    pokemonListLiveData = pokemonFetchingLiveData.switchMap {
      isLoading.set(true)
      launchOnViewModelScope {
        this.mainRepository.fetchPokemonList(
          page = it,
          onSuccess = { isLoading.set(false) },
          onError = { toastLiveData.postValue(it) }
        ).asLiveData()
      }
    }
  }

  fun fetchPokemonList(page: Int) {
    pokemonFetchingLiveData.value = page
  }
}
