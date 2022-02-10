package com.finite.komalmatade

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.komalmatade.databinding.FragmentLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            //sharedVm = viewModel
            //baseFragment = this@BaseFragment
        }

        binding!!.loginBtn.setOnClickListener {

            Log.d("checkStage:", "onClick Reached")

            //Toast.makeText(here.context, "button click", Toast.LENGTH_SHORT).show()

            val number = binding!!.etLoginmobileno.text
            val pass = binding!!.etLoginpassword.text

            //Toast.makeText(requireContext(),"$number : $pass", Toast.LENGTH_SHORT).show()

            val database = Firebase.database
            val refer = database.getReference("users")


            // Read from the database
            refer.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("checkStage:", "onDataChanged Reached")
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val children = snapshot!!.children
                    children.forEach {

                        val dbno = it.child("number").value.toString()
                        val dbpass = it.child("password").value.toString()

                        Log.d("checkStage:", "forEach Reached")

                        //Toast.makeText(requireContext(), "$dbno db, $number no = $dbpass db, $pass pass", Toast.LENGTH_SHORT).show()

                        if(dbno.contentEquals(number) && dbpass.contentEquals(pass)) {
                            Log.d("checkStage:", "pass=no Reached")
                            //Toast.makeText(here.context,"Passed", Toast.LENGTH_SHORT).show()
                            //Success
                            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
                            with (sharedPref.edit()) {
                                putString("cu",dbno)
                                apply()
                            }

                            vm.currentUser = it.child("number").value.toString()
                            vm.address = it.child("address").value.toString()
                            vm.bg = it.child("bg").value.toString()
                            vm.desig = it.child("desig").value.toString()
                            vm.dob = it.child("dob").value.toString()
                            vm.doj = it.child("doj").value.toString()
                            vm.name = it.child("name").value.toString()
                            vm.ntss = it.child("ntss").value.toString()
                            vm.pfoe = it.child("pfoe").value.toString()
                            vm.picurl = it.child("picurl").value.toString()
                            vm.qual = it.child("qual").value.toString()
                            vm.salary = it.child("salary").value.toString()
                            vm.tturl = it.child("tturl").value.toString()
                            vm.type = it.child("type").value.toString()
                            vm.att = it.child("att").value.toString()

                            if(vm.type == "faculty") {
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToFacultyHome())
                                //myRef.removeEventListener(this)
                            } else if(vm.type == "hod") {
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHodHome())
                                //myRef.removeEventListener(this)
                            }
                        }
                    }

                    if(vm.currentUser == "none") {
                        Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }

            })
        }

    }

}