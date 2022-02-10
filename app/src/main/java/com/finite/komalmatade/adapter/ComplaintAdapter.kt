package com.finite.komalmatade.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finite.komalmatade.R
import com.finite.komalmatade.model.ComplaintModel

class ComplaintAdapter(private val compList : ArrayList<ComplaintModel>): RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComplaintViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.complaint_singlerow, parent, false)
        return ComplaintViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val currentitem = compList[position]
        holder.message.text = currentitem.message
        holder.author.text = currentitem.author
        /*holder.cancel.setOnClickListener {

        }*/
    }

    override fun getItemCount(): Int {
        return compList.size
    }

    class ComplaintViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var message : TextView = itemView.findViewById(R.id.compBody)
        var author: TextView = itemView.findViewById(R.id.compName)
        //var cancel: ImageView = itemView.findViewById(R.id.cancelBtn)

    }
}