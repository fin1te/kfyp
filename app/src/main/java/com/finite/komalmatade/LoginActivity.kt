package com.finite.komalmatade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels


class LoginActivity : AppCompatActivity() {

    private val vm: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding : ActivityLoginBinding =
            //DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(R.layout.activity_login)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.topbarbrown)

        /*
        binding.loginBtn.setOnClickListener {here ->

            //Toast.makeText(here.context, "button click", Toast.LENGTH_SHORT).show()

            val number = binding.etLoginmobileno.text
            val pass = binding.etLoginpassword.text

            val database = Firebase.database
            val myRef = database.getReference("users")
            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    val children = snapshot!!.children
                    children.forEach {

                        val dbno = it.child("number").value.toString()
                        val dbpass = it.child("password").value.toString()

                        //Toast.makeText(here.context, "$dbno db, $number no = $dbpass db, $pass pass", Toast.LENGTH_SHORT).show()

                        if(dbno.contentEquals(number) && dbpass.contentEquals(pass)) {
                            //Toast.makeText(here.context,"Passed", Toast.LENGTH_SHORT).show()
                            //Success

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to read value.", error.toException())
                }

            })
        }
        */



    }
}