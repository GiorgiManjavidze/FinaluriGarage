package com.example.my_popular_garage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var editTextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerListeners()

    }

    private fun init() {
        editTextName = findViewById(R.id.editTextTextPersonName)
        editTextEmail = findViewById(R.id.editTextTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextTextPassword)
        editTextPassword2 = findViewById(R.id.editTextTextPassword2)
        editTextButton = findViewById(R.id.button3)
    }

    private fun registerListeners() {
        editTextButton.setOnClickListener{

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password2 = editTextPassword2.text.toString()


            if (email.isEmpty()) {
                editTextEmail.error = "შეიყვანეთ მეილი!"
                return@setOnClickListener
            }
            else if (password != password2) {
                Toast.makeText(this, "პაროლები უნდა ემთხვეოდეს ერთმანეთს!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (email.length<8){
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
                return@setOnClickListener
            }
            else if (!email.contains("@") || !email.contains(".")){
                editTextEmail.error = "მეილი არასწორადაა შეყვანილი"
            }
            else if (password.isEmpty()){
                editTextPassword.error = "შეიყვანეთ პაროლი"
                return@setOnClickListener
            }
            else if (password2.isEmpty()) {
                editTextPassword2.error = "შეიყვანეთ პაროლი"
                return@setOnClickListener
            }
            else if(password.length<8){
                editTextPassword.error = "პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this, Authorization::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

}