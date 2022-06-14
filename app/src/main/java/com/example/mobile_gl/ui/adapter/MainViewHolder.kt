package com.example.mobile_gl.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gl.data.model.MainResponse
import com.example.mobile_gl.databinding.ItemListBinding
import com.squareup.picasso.Picasso


class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemListBinding.bind(view)
    val cardContainer: CardView = binding.cardContainer
    val itemTitle: TextView = binding.tvListTitle
    val itemDescription: TextView = binding.tvListDescription
    val itemImage: ImageView = binding.ivListImage

    fun bind(list: MainResponse) {
        if (!list.image.isNullOrBlank()) Picasso.get().load(list.image).into(itemImage)
        else itemImage.visibility = View.GONE
        itemTitle.text = list.title
        itemDescription.text = list.description
    }
}