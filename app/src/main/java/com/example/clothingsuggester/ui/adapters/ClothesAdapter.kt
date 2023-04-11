package com.example.clothingsuggester.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingsuggester.R
import com.example.clothingsuggester.databinding.ClothesItemBinding
import com.example.clothingsuggester.domain.Cloth

class ClothesAdapter(val list: List<Cloth>):RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        return ClothesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.clothes_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        holder.binding.imageClothes.setImageResource(list[position].imageSource)
    }

    class ClothesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ClothesItemBinding.bind(viewItem)
    }

}