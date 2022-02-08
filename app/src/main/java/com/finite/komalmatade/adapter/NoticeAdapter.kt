package com.finite.komalmatade.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finite.komalmatade.R
import com.finite.komalmatade.model.NoticeModel

class NoticeAdapter(private val noticeList : ArrayList<NoticeModel>) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.notice_singlerow, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val currentitem = noticeList[position]

        holder.noticeTitle.text = currentitem.noticeTitle
        holder.noticeDate.text = currentitem.noticeDate
        holder.noticeBody.text = currentitem.noticeBody
        holder.noticeAuthor.text = currentitem.noticeAuthor
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    class NoticeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var noticeTitle : TextView = itemView.findViewById(R.id.noticeTitle)
        var noticeDate:TextView = itemView.findViewById(R.id.noticeDate)
        var noticeBody:TextView = itemView.findViewById(R.id.noticeBody)
        var noticeAuthor:TextView = itemView.findViewById(R.id.noticeAuthor)

    }

}