package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finite.komalmatade.adapter.NoticeAdapter
import com.finite.komalmatade.databinding.FragmentViewNoticeBinding
import com.finite.komalmatade.model.NoticeModel
import com.google.firebase.database.*

class ViewNoticeFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var noticeRecView : RecyclerView
    private lateinit var userArrayList : ArrayList<NoticeModel>
    private var binding : FragmentViewNoticeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentViewNoticeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeRecView = binding!!.noticerecview
        noticeRecView.layoutManager = LinearLayoutManager(requireContext())
        noticeRecView.setHasFixedSize(true)

        userArrayList = arrayListOf<NoticeModel>()
        getUserData()
    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("notice")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(NoticeModel::class.java)
                        userArrayList.add(user!!)

                    }
                    noticeRecView.adapter = NoticeAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

}