package com.example.my_popular_garage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Authorization : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        init()
        loginListeners()

        val buttonReset = findViewById<Button>(R.id.button5)
        buttonReset.setOnClickListener{
            val intent = Intent(this, Reset_password::class.java)
            startActivity(intent)
        }

    }

    private fun init() {

        editTextEmail = findViewById(R.id.editTextTextEmailAddress2)
        editTextPassword = findViewById(R.id.editTextTextPassword3)
        buttonLogin = findViewById(R.id.button4)

    }

    private fun loginListeners() {

        buttonLogin.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "შეავსეთ ველები"
                return@setOnClickListener
            }
            else if (email.length<8 || password.length<8){
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
                editTextPassword.error = "პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს"
                return@setOnClickListener
            }
            else if (!email.contains("@") || !email.contains(".")){
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
            }
            else if(password.isEmpty()){
                editTextPassword.error = "შეავსეთ პაროლი!"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, Menu::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

        }

    }

    private fun menu() {
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }


}