package com.sburnadze.final_project_messenger_app.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sburnadze.final_project_messenger_app.AuthorizationModel
import com.sburnadze.final_project_messenger_app.R

class SignUpActivity: AppCompatActivity() {
    private lateinit var nameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var whatIDo: EditText
    private lateinit var image: ImageView
    private lateinit var signUpButton: Button
    private lateinit var authorizationViewModel: AuthorizationModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initView()
    }

    private fun initView() {
        authorizationViewModel = AuthorizationModel(this)
        nameText = findViewById(R.id.register_username_edit_text)
        passwordText = findViewById(R.id.register_password_edit_text)
        whatIDo = findViewById(R.id.register_what_i_do_edit_text)
        image = findViewById(R.id.register_image_view)
        signUpButton = findViewById(R.id.sign_up_button)
        signUpButton.setOnClickListener{
            signUp()
        }
    }

    private fun signUp(){
        val name = nameText.text.toString()
        val password = passwordText.text.toString()
        val whatIDo = whatIDo.text.toString()
        val image = image
        if(check(name, password, whatIDo)) {
            authorizationViewModel.registerUser(name, password, whatIDo, image)
        }

    }

    private fun check(name: String, password: String, whatIDo: String): Boolean {
        if(name.isEmpty()){
            Toast.makeText(this,"enter name", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.isEmpty()){
            Toast.makeText(this,"enter password", Toast.LENGTH_SHORT).show()
            return false
        }

        if(whatIDo.isEmpty()){
            Toast.makeText(this,"enter profession", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}