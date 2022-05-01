package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finite.komalmatade.adapter.NoticeAdapter
import com.finite.komalmatade.adapter.UserAdapter
import com.finite.komalmatade.databinding.FragmentSearchFacultyBinding
import com.finite.komalmatade.databinding.FragmentViewProfileBinding
import com.finite.komalmatade.model.NoticeModel
import com.finite.komalmatade.model.SearchUserModel
import com.google.firebase.database.*

class SearchFacultyFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecView : RecyclerView
    private lateinit var userArrayList : ArrayList<SearchUserModel>
    var binding : FragmentSearchFacultyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchFacultyBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecView = binding!!.userRecView
        userRecView.layoutManager = LinearLayoutManager(requireContext())
        userRecView.setHasFixedSize(true)

        userArrayList = arrayListOf<SearchUserModel>()
        getUserData()
    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("users")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val user = SearchUserModel()
                        user.name = userSnapshot.child("name").value.toString()
                        user.qual = userSnapshot.child("qual").value.toString()
                        user.number = userSnapshot.child("number").value.toString()
                        user.emailid = userSnapshot.child("emailid").value.toString()
                        user.picurl = userSnapshot.child("picurl").value.toString()

                        userArrayList.add(user)

                    }
                    userRecView.adapter = UserAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}