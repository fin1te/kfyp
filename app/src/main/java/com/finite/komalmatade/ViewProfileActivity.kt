package com.finite.komalmatade

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.finite.komalmatade.databinding.ActivityScanBinding
import com.finite.komalmatade.databinding.ActivityViewProfileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val userCode = sharedPreference.getString("currentCode","HE008954").toString()

        val database = Firebase.database
        val refer = database.getReference("users").child(userCode)

        refer.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()) {

                    binding!!.apply {
                        nameTitle.text = snapshot.child("name").value.toString()
                        fullName.text = snapshot.child("name").value.toString()
                        number.text = snapshot.child("number").value.toString()
                        desig.text = snapshot.child("desig").value.toString()
                        dob.text = snapshot.child("dob").value.toString()
                        doj.text = snapshot.child("doj").value.toString()
                        qual.text = snapshot.child("qual").value.toString()
                        bg.text = snapshot.child("bg").value.toString()
                        address.text = snapshot.child("address").value.toString()
                        salary.text = snapshot.child("salary").value.toString()
                        ntss.text = snapshot.child("ntss").value.toString()
                        pfoe.text = snapshot.child("pfoe").value.toString()
                        att.text = snapshot.child("att").value.toString()
                    }
                    Glide.with(this@ViewProfileActivity).load(snapshot.child("picurl").value.toString()).into(binding.circleImageProfile)


                } else {
                    Toast.makeText(this@ViewProfileActivity, "Error! No user found!", Toast.LENGTH_LONG).show()
                    finish()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}