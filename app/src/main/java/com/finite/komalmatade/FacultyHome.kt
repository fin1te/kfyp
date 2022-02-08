package com.finite.komalmatade

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.finite.komalmatade.databinding.FragmentFacultyHomeBinding
import com.finite.komalmatade.databinding.FragmentLoginBinding


class FacultyHome : Fragment() {
    private var binding : FragmentFacultyHomeBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFacultyHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            vm = vm
            lifecycleOwner = viewLifecycleOwner
            //baseFragment = this@BaseFragment

        }

        Glide.with(this).load(vm.picurl).into(binding!!.userImg)
        binding!!.tvname.text = vm.name
        binding!!.tvdesig.text = vm.desig

        //binding!!.testtv.text = vm.currentUser

        binding!!.ttBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vm.tturl))
            startActivity(browserIntent)
        }

        binding!!.notifBtn.setOnClickListener {
            findNavController().navigate(FacultyHomeDirections.actionFacultyHomeToViewNoticeFragment())
        }

        binding!!.compBtn.setOnClickListener {
            Toast.makeText(this.context, "Under Construction!", Toast.LENGTH_SHORT).show()
        }

    }

}