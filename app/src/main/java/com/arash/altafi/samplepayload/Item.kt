package com.arash.altafi.samplepayload

import androidx.annotation.DrawableRes

data class Item(
    val id: String,
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val isFavorite: Boolean
)