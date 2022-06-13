package com.example.projectfinalversion

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectfinalversion.databinding.ActivityDeliveryDateBinding
import java.lang.Exception
import java.util.*

class DeliveryDateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDeliveryDateBinding
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private lateinit var sqLiteStatement: SQLiteStatement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            val intent = intent
            booksImageView.setImageResource(intent.getIntExtra("Books Image",0))
            booksNameText.text = intent.getStringExtra("Books Name")
            booksAuthorText.text = intent.getStringExtra("Books Author")
            selectedDate.setOnClickListener {

                val datePickerDialog = DatePickerDialog(this@DeliveryDateActivity,DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    selectedDate.text = "$day / ${month + 1} / $year"
                },year,month,day)
                datePickerDialog.show()
            }
            borrowButton.setOnClickListener {
                if (selectedDate.text.isNullOrEmpty()) {
                    Toast.makeText(this@DeliveryDateActivity,"Please, select date",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DeliveryDateActivity,"${booksNameText.text} borrowed",Toast.LENGTH_SHORT).show()
                    saveBorrowedBooks(booksNameText.text.toString(),booksAuthorText.text.toString(),selectedDate.text.toString())
                    val intent = Intent(this@DeliveryDateActivity,LibraryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun saveBorrowedBooks(bookName:String, bookAuthor:String, borrowedDate: String) {

        try {
            sqLiteDatabase = openOrCreateDatabase("Borrowed Books", Context.MODE_PRIVATE,null)
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS borrowedBooks(id INTEGER PRIMARY KEY, bookName VARCHAR, bookAuthor VARCHAR, borrowedDate VARCHAR)")
            val sqlString = "INSERT INTO borrowedBooks(bookName,bookAuthor,borrowedDate) VALUES(?,?,?)"
            sqLiteStatement = sqLiteDatabase.compileStatement(sqlString)
            sqLiteStatement.bindString(1,bookName)
            sqLiteStatement.bindString(2,bookAuthor)
            sqLiteStatement.bindString(3,borrowedDate)
            sqLiteStatement.execute()
            println("Datebase oluşturuldu")
        } catch (e:Exception) {
            e.printStackTrace()
        }

    }



}