package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finite.komalmatade.adapter.ComplaintAdapter
import com.finite.komalmatade.adapter.NoticeAdapter
import com.finite.komalmatade.databinding.FragmentViewComplaintBinding
import com.finite.komalmatade.model.ComplaintModel
import com.finite.komalmatade.model.NoticeModel
import com.google.firebase.database.*

class ViewComplaintFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var comprecview : RecyclerView
    private lateinit var comparraylist : ArrayList<ComplaintModel>
    var binding: FragmentViewComplaintBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewComplaintBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comprecview = binding!!.comprecview
        comprecview.layoutManager = LinearLayoutManager(requireContext())
        comprecview.setHasFixedSize(true)

        comparraylist = arrayListOf<ComplaintModel>()
        getData()

    }

    private fun getData() {

        dbref = FirebaseDatabase.getInstance().getReference("complaints")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val comp = userSnapshot.getValue(ComplaintModel::class.java)
                        comparraylist.add(comp!!)

                    }
                    comprecview.adapter = ComplaintAdapter(comparraylist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}