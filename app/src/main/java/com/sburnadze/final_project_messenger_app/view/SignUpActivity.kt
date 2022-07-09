package com.sburnadze.final_project_messenger_app.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.sburnadze.final_project_messenger_app.R

class SignUpActivity: AppCompatActivity() {
    private lateinit var nameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var whatIDo: EditText
    private lateinit var signUpButton: Button
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_registration)
        initView()
    }

    private fun initView() {
        signUpButton = findViewById(R.id.sign_up_button)
        signUpButton.setOnClickListener{
            signUp()
        }
    }

    private fun signUp(){
        val name = nameText.text.toString()
        val password = passwordText.text.toString()
        val whatIDo = whatIDo.text.toString()
        //signup todo
    }
}