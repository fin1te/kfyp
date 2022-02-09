package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.finite.komalmatade.databinding.FragmentReportComplaintsBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ReportComplaintsFragment : Fragment() {

    var binding: FragmentReportComplaintsBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReportComplaintsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = Firebase.database
        val myRef = database.getReference("complaints")

        binding!!.sendButton.setOnClickListener {
            if(binding!!.etcomplaints.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Enter a message!", Toast.LENGTH_SHORT).show()
            } else {
                val text = binding!!.etcomplaints.text.toString()
                val unixTime = System.currentTimeMillis()
                myRef.child(unixTime.toString()).child("message").setValue(text)
                myRef.child(unixTime.toString()).child("author").setValue(vm.name)
            }
        }

    }
}