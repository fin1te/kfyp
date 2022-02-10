package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.finite.komalmatade.databinding.FragmentViewProfileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ViewProfileFragment : Fragment() {

    var binding : FragmentViewProfileBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.apply {
            nameTitle.text = vm.name
            fullName.text = vm.name
            number.text = vm.currentUser
            desig.text = vm.desig
            dob.text = vm.dob
            doj.text = vm.doj
            qual.text = vm.qual
            bg.text = vm.bg
            address.text = vm.address
            salary.text = vm.salary
            ntss.text = vm.ntss
            pfoe.text = vm.pfoe
            att.text = vm.att
        }
        Glide.with(this).load(vm.picurl).into(binding!!.circleImageProfile)
    }
}