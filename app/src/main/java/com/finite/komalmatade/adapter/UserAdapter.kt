package com.finite.komalmatade.adapter

import android.content.Intent
import android.net.Uri
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finite.komalmatade.R
import com.finite.komalmatade.model.SearchUserModel
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class UserAdapter(private val userlist : ArrayList<SearchUserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_singlerow, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentitem = userlist[position]

        holder.searchname.text = currentitem.name
        holder.searchdetail.text = currentitem.qual
        holder.searchemail1.text = currentitem.emailid
        holder.searchcontact1.text = currentitem.number

        Glide.with(holder.itemView.context).load(currentitem.picurl).into(holder.singleImageView)

    }

    override fun getItemCount(): Int {
        return userlist.size
    }

     class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var searchname : TextView = itemView.findViewById(R.id.searchname)
        var searchdetail: TextView = itemView.findViewById(R.id.searchdetail)
        var iv_whatsapp: ImageView = itemView.findViewById(R.id.iv_whatsapp)
        var iv_call: ImageView = itemView.findViewById(R.id.iv_call)
        var iv_mail: ImageView = itemView.findViewById(R.id.iv_mail)
        var singleImageView : CircleImageView = itemView.findViewById(R.id.singleImageView)

         var searchcontact1 : TextView = itemView.findViewById(R.id.searchcontact1)
         var searchemail1 : TextView = itemView.findViewById(R.id.searchemail1)


         init {
             iv_whatsapp.setOnClickListener { v ->
                 v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                 val wame: String
                 wame = "https://wa.me/91" + searchcontact1.text.toString()
                 val intent = Intent(Intent.ACTION_VIEW)
                 intent.data = Uri.parse(wame)
                 v.context.startActivity(intent)
             }
             iv_mail.setOnClickListener { v ->
                 v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                 val mailto: String
                 mailto = "mailto:" +  searchemail1.text.toString() + "?subject=\""
                 val intent = Intent(Intent.ACTION_VIEW)
                 intent.data = Uri.parse(mailto)
                 v.context.startActivity(intent)
             }
             iv_call.setOnClickListener { v ->
                 v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                 val ctc: String
                 ctc = "tel:+91" + searchcontact1.text.toString()
                 val intent = Intent(Intent.ACTION_DIAL)
                 intent.data = Uri.parse(ctc)
                 v.context.startActivity(intent)
             }
         }
     }

}