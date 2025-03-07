package com.example.fetchrewards

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(private var items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.name ?: "No Name"
    }

    override fun getItemCount() = items.size

    class ItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        fun bind(item: Item) {
            textView.text = item.name ?: "No Name"
        }
    }
}
