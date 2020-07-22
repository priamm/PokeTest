package com.priamm.poketest.extensions

import android.view.View

fun View.gone(shouldBeGone: Boolean) {
  visibility = if (shouldBeGone) {
    View.GONE
  } else {
    View.VISIBLE
  }
}
