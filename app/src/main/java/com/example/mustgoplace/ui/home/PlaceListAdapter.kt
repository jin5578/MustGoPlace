package com.example.mustgoplace.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mustgoplace.databinding.ItemPlaceBinding
import com.example.mustgoplace.model.Place

class PlaceListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) : ListAdapter<Place, PlaceViewHolder>(PlaceDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding, lifecycleOwner, viewModel)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object PlaceDiff : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

}

class PlaceViewHolder(
    private val binding: ItemPlaceBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Place) {
        binding.item = item
        binding.lifecycleOwner = lifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}