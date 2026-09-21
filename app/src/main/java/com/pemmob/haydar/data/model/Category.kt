package com.pemmob.haydar.data.model

import android.content.ClipDescription

data class Category(
    val id: Int,
    val name: String,
    val description: String?,   // tanda tanya sebagai null safety supaya ttp diisi string kosong agar tidak error.
    val products_count: Int?
)
