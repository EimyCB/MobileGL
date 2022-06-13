package com.example.mobile_gl.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_gl.R
import com.example.mobile_gl.data.model.MainResponse

class MainAdapter(
    private var list: List<MainResponse>,
    private val onClick: (cardClicked: MainResponse) -> Unit
): RecyclerView.Adapter<MainViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemList = list[position]
        holder.bind(itemList)

        holder.cardContainer.setOnClickListener{
            onClick.invoke(itemList)
        }
    }

    override fun getItemCount(): Int = list.size
}