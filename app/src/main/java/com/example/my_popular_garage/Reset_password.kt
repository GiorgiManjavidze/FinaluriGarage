package com.example.my_popular_garage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Reset_password : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        init()

        registerListeners()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextTextEmailAddress3)
        buttonSend = findViewById(R.id.button6)
    }

    private fun registerListeners() {
        buttonSend.setOnClickListener {

            val email = editTextEmail.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "შეიყვანეთ მეილი"
                return@setOnClickListener
            }
            else if (!email.contains("@")) {
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
                return@setOnClickListener
            }
            else if (!email.contains(".")) {
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
                return@setOnClickListener
            }
            else if (email.length<8) {
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val buttonReset = findViewById<Button>(R.id.button5)
                        buttonReset.setOnClickListener {
                            val intent = Intent(this, Authorization::class.java)
                            startActivity(intent)
                        }

                    }
                }

        }
    }
}