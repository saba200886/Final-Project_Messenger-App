package com.sburnadze.final_project_messenger_app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.AuthorizationModel
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.User


class ProfilePageFragment(var currUser: String): Fragment() {

    private lateinit var nameText: EditText
    private lateinit var whatIDoText: EditText
    lateinit var profilePicture: ImageView
    private lateinit var authorizationViewModel: AuthorizationModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        nameText = view.findViewById(R.id.profile_username_edit_text)
        whatIDoText = view.findViewById(R.id.profile_what_i_do_edit_text)
        profilePicture = view.findViewById(R.id.profile_image_view)
        authorizationViewModel = AuthorizationModel(MainPageActivity())
        view.findViewById<AppCompatButton>(R.id.sign_out_button)
            .setOnClickListener{
                authorizationViewModel.signOut()
                startActivity(Intent(this.activity, LoginActivity::class.java))
                activity?.finish()
            }
        view.findViewById<Button>(R.id.update_button).setOnClickListener{
            authorizationViewModel.update(nameText.text.toString(), whatIDoText.text.toString())
        }

        showCurrUser()
    }


    private fun showCurrUser(){
        val users = Firebase.database.getReference("users")

        users.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val currentUser = it.getValue(User::class.java) as User
                    if(currentUser.id == currUser){
                        nameText.setText(currentUser.name.toString())
                        whatIDoText.setText(currentUser.profession.toString())

                        return
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Search Error", "Error occurred in finding user", error.toException())
            }

        })
    }

}