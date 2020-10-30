package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RCView(var list: List<String>) : RecyclerView.Adapter<RCView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RCView.ViewHolder {
        return ViewHolder.Companion.from(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RCView.ViewHolder, position: Int) {
        holder.textView.setText(list[position])
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var textView: TextView

        init{
            textView = itemView.findViewById(R.id.card_textView)
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                var inflater = LayoutInflater.from(parent.context)
                var view = inflater.inflate(R.layout.cards, parent, false)
                return ViewHolder(view)
            }
        }
    }
}