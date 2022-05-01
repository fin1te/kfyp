package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finite.komalmatade.adapter.NoticeAdapter
import com.finite.komalmatade.databinding.FragmentShowLogBinding
import com.finite.komalmatade.databinding.FragmentViewNoticeBinding
import com.finite.komalmatade.model.NoticeModel
import com.google.firebase.database.*

class ShowLogFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var dbref2 : DatabaseReference
    private lateinit var dbref3 : DatabaseReference
    private var binding : FragmentShowLogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowLogBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dbref = FirebaseDatabase.getInstance().getReference("notice")
        dbref2 = FirebaseDatabase.getInstance().getReference("complaints")
        dbref3 = FirebaseDatabase.getInstance().getReference("users")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var value = 0
                    for (userSnapshot in snapshot.children){
                        value++
                    }
                    binding!!.totalNotice.text = value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbref2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var value = 0
                    for (userSnapshot in snapshot.children){
                        value++
                    }
                    binding!!.totalComp.text = value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbref3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var value = 0
                    for (userSnapshot in snapshot.children){
                        value++
                    }
                    binding!!.totalFaculties.text = value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}