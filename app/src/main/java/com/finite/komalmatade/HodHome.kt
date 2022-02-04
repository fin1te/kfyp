package com.finite.komalmatade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.finite.komalmatade.databinding.FragmentFacultyHomeBinding
import com.finite.komalmatade.databinding.FragmentHodHomeBinding


class HodHome : Fragment() {

    private var binding : FragmentHodHomeBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHodHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            //baseFragment = this@BaseFragment
        }

        Glide.with(this).load(vm.picurl).into(binding!!.userImg)
        binding!!.tvname.text = vm.name
        binding!!.tvdesig.text = vm.desig

        //binding!!.testtv.text = vm.currentUser

    }

}