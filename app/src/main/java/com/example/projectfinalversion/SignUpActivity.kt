package com.example.projectfinalversion

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectfinalversion.databinding.ActivitySignUpBinding
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private lateinit var sqLiteStatement: SQLiteStatement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            signUpButton.setOnClickListener {
                if (signUpNameText.text.isNullOrEmpty() || signUpEmailEditText.text.isNullOrEmpty() || signUpPasswordEditText.text.isNullOrEmpty()) {
                    Toast.makeText(this@SignUpActivity,"Please, don't leave blank!",Toast.LENGTH_LONG).show()
                } else {
                    val userEmail = signUpEmailEditText.text.toString()
                    val userPassword = signUpPasswordEditText.text.toString()
                    saveUser(userEmail,userPassword)
                    val intent = Intent(this@SignUpActivity,LibraryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            signUpLoginButton.setOnClickListener {
                val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }


    private fun saveUser(userEmail:String, userPassword:String) {

        try {
            sqLiteDatabase = openOrCreateDatabase("Users",Context.MODE_PRIVATE,null)
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, userEmail VARCHAR, userPassword VARCHAR)")
            val sqlString = "INSERT INTO users(userEmail,userPassword) VALUES(?,?)"
            sqLiteStatement = sqLiteDatabase.compileStatement(sqlString)
            sqLiteStatement.bindString(1,userEmail)
            sqLiteStatement.bindString(2,userPassword)
            sqLiteStatement.execute()
            println("$userEmail Sqlite veri tabanına kaydedildi.")
        } catch (exception:Exception) {
            exception.printStackTrace()
        }

    }


}