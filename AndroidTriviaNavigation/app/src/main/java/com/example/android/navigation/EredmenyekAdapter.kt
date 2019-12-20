package com.example.android.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.database.EredmenyEntity


class EredmenyekAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data =  listOf<EredmenyEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.name1.toString() + ", "+item.name2.toString() + ", " +item.pont.toString()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.text_item_view_holder, parent, false) as TextView
            return TextItemViewHolder(view)
        }


}