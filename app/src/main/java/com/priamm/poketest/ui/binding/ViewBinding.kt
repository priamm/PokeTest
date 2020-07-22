package com.priamm.poketest.ui.binding

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.priamm.poketest.extensions.gone
import com.skydoves.whatif.whatIfNotNull

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
  text.value.whatIfNotNull {
    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
  }
}

@BindingAdapter("paletteImage", "paletteCard")
fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
  Glide.with(view.context)
    .load(url)
    .listener(GlidePalette.with(url)
      .use(BitmapPalette.Profile.MUTED_LIGHT)
      .intoCallBack { palette ->
        val rgb = palette?.dominantSwatch?.rgb
        if (rgb != null) {
          paletteCard.setCardBackgroundColor(rgb)
        }
      }
      .crossfade(true))
    .into(view)
}


@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
  view.gone(shouldBeGone)
}

