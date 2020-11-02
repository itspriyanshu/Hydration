package com.example.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RCView(var list: List<String>, var context: Context) : RecyclerView.Adapter<RCView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RCView.ViewHolder {
        return ViewHolder.Companion.from(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RCView.ViewHolder, position: Int) {
        holder.time.setText(list[position])
        holder.number.setText("1")
        holder.glasses.removeAllViews()
        var params = LinearLayout.LayoutParams(60,60)
        for(x in 1..3){
            var imageView = ImageView(context)
            imageView.setImageResource(R.drawable.ic_local_drink_grey_120px)
            imageView.layoutParams = params
            holder.glasses.addView(imageView)
        }
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var time: TextView
        lateinit var number: TextView
        lateinit var glasses: LinearLayout

        init{
            time = itemView.findViewById(R.id.card_textView)
            number = itemView.findViewById(R.id.number)
            glasses = itemView.findViewById(R.id.glasses_layout)
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