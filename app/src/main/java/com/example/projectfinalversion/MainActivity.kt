package com.example.projectfinalversion

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectfinalversion.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPrefencesEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Is Login", Context.MODE_PRIVATE)
        sharedPrefencesEditor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean("Log In", false)) {
            val intent = Intent(this@MainActivity, LibraryActivity::class.java)
            startActivity(intent)
            finish()
        }

        with(binding) {

            loginButton.setOnClickListener {

                if (loginEmailEditText.text.isNullOrEmpty() || loginPasswordEditText.text.isNullOrEmpty()) {
                    Toast.makeText(this@MainActivity, "Please, don't leave blank!", Toast.LENGTH_SHORT).show()
                } else {
                    val getUserEmail = loginEmailEditText.text.toString()
                    val getUserPassword = loginPasswordEditText.text.toString()
                    getUser(getUserEmail, getUserPassword)
                    sharedPrefencesEditor.putBoolean("Log In", true)
                    sharedPrefencesEditor.apply()
                }

            }

            loginSignUpButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }


    private fun getUser(userEmail: String, userPassword: String) {

        try {
            sqLiteDatabase = openOrCreateDatabase("Users", Context.MODE_PRIVATE, null)
            val cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null)
            val idIndex = cursor.getColumnIndex("id")
            val userEmailIndex = cursor.getColumnIndex("userEmail")
            val userPasswordIndex = cursor.getColumnIndex("userPassword")

            while (cursor.moveToNext()) {

                val cursorID = cursor.getString(idIndex)
                val cursorUserEmail = cursor.getString(userEmailIndex)
                val cursorUserPassword = cursor.getString(userPasswordIndex)
                println("ID : $cursorID User Email : $cursorUserEmail User Password : $cursorUserPassword")
                if (userEmail == cursorUserEmail && userPassword == cursorUserPassword) {
                    cursor.close()
                    val intent = Intent(this@MainActivity, LibraryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}