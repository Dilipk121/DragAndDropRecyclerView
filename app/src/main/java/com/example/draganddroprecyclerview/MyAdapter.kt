package com.example.draganddroprecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val itemArrayList: ArrayList<DragAndDrop>) : RecyclerView.Adapter<MyAdapter.myViewHolder>() {


    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val tvTxt = itemView.findViewById<TextView>(R.id.tv_txt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val item_view = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        return myViewHolder(item_view)
    }

    override fun getItemCount(): Int {
       return itemArrayList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
      //  val currentItem = itemArrayList[position]

        holder.tvTxt.text =  itemArrayList[position].position //we add direct here bcoz only a single data here
    }

    fun deleteItems(adapterPosition: Int) {

        itemArrayList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)

    }


}