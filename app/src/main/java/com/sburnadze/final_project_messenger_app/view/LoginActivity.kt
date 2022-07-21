package com.sburnadze.final_project_messenger_app.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.sburnadze.final_project_messenger_app.AuthorizationModel
import com.sburnadze.final_project_messenger_app.R

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBut: Button
    private lateinit var signUpBut: AppCompatButton
    private lateinit var authorizationViewModel: AuthorizationModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

       /* val database = Firebase.database
        val myRef = database.getReference("message")
        val userReference = database.getReference("UserList")


        myRef.setValue("Hello, World1!")

        userReference.push().key?.let{
            userReference.child(it).setValue(User("saba","saba1234", "auditor"))
        }



        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "count is:" + dataSnapshot.children.count())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
*/

    }

    private fun initView() {
        username = findViewById(R.id.login_username_edit_text)
        password = findViewById(R.id.login_password_edit_text)
        loginBut = findViewById(R.id.login_button)
        signUpBut = findViewById(R.id.login_page_signup_button)

        authorizationViewModel = AuthorizationModel(this)


        loginBut.setOnClickListener{
            login()
        }

        signUpBut.setOnClickListener{
            openRegistrationPage()
        }
    }


    //user logs in messenger app if username and password are valid
    private fun login() {
        val user = username.text.toString()
        val pass = password.text.toString()

        if(valid(user, pass)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                authorizationViewModel.loginUser(user, pass)
            }
        }
    }



    //check if password and username is valid
    private fun valid(user: String, pass: String): Boolean {
        if(user.isNotEmpty() && pass.isNotEmpty())
            return true
        return false
    }


    //open registration page for user to register
    private fun openRegistrationPage() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

}
