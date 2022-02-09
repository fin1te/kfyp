package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.komalmatade.databinding.FragmentCreateNoticeBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CreateNoticeFragment : Fragment() {

    var binding : FragmentCreateNoticeBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoticeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val database = Firebase.database
        val myRef = database.getReference("notice")

        binding!!.sendButton.setOnClickListener {

            val title = binding!!.etNoticeTitle.text.toString()
            val body = binding!!.etBody.text.toString()
            val date = binding!!.etDate.text.toString()
            val dept = binding!!.etDept.text.toString()

            if(title.isEmpty()||body.isEmpty()||date.isEmpty()||dept.isEmpty()) {
                Toast.makeText(requireContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            } else {
                val unixTime = System.currentTimeMillis()
                myRef.child("$unixTime").child("department").setValue(dept)
                myRef.child("$unixTime").child("noticeAuthor").setValue(vm.name)
                myRef.child("$unixTime").child("noticeBody").setValue(body)
                myRef.child("$unixTime").child("noticeDate").setValue(date)
                myRef.child("$unixTime").child("noticeTitle").setValue(title)

                Toast.makeText(requireContext(), "Notice Posted Successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(CreateNoticeFragmentDirections.actionCreateNoticeFragmentToHodHome())
            }
        }

    }
}