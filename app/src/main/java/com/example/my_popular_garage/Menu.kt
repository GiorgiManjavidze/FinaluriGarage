package com.example.my_popular_garage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.my_popular_garage.R
import com.google.firebase.auth.FirebaseAuth

class Menu : AppCompatActivity() {


    private lateinit var buttonCars : Button
    private lateinit var buttonMyGarage: Button
    private lateinit var buttonProfile: Button
    private lateinit var buttonParameters : Button



    private lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        init()
        registerListeners()
        profileListeners()

    }

    private fun init() {
        buttonLogout = findViewById(R.id.button12)
        buttonCars = findViewById(R.id.button7)
        buttonMyGarage = findViewById(R.id.button8)
        buttonProfile = findViewById(R.id.button9)
        buttonParameters = findViewById(R.id.button11)
    }


    private fun registerListeners() {
        buttonLogout.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun profileListeners() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

}