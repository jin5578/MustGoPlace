package com.example.mustgoplace.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mustgoplace.model.Place

@BindingAdapter("placeList")
fun placeList(recyclerView: RecyclerView, list: List<Place>?) {
    list?.let {
        (recyclerView.adapter as PlaceListAdapter).submitList(it)
    }
}