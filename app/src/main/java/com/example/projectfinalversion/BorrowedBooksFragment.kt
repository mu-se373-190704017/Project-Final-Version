package com.example.projectfinalversion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectfinalversion.databinding.FragmentBorrowedBooksBinding
import java.lang.Exception

class BorrowedBooksFragment  : Fragment() {


    private lateinit var binding : FragmentBorrowedBooksBinding
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private lateinit var arrayList : ArrayList<BorrowedBooksModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBorrowedBooksBinding.inflate(layoutInflater)
        arrayList = ArrayList()
        getBorrowedBooks()
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val borrowedAdapter = BorrowedAdapter(requireContext(),arrayList)
            recyclerView.adapter = borrowedAdapter
        }

        return binding.root
    }


    private fun getBorrowedBooks() {

        try {
            sqLiteDatabase = requireActivity().openOrCreateDatabase("Borrowed Books", Context.MODE_PRIVATE,null)
            val cursor = sqLiteDatabase.rawQuery("SELECT * FROM borrowedBooks",null)
            val idIndex = cursor.getColumnIndex("id")
            val bookNameIndex = cursor.getColumnIndex("bookName")
            val bookAuthorIndex = cursor.getColumnIndex("bookAuthor")
            val borrowedDateIndex = cursor.getColumnIndex("borrowedDate")

            while (cursor.moveToNext()) {
                val cursorID = cursor.getString(idIndex)
                val cursorBookName = cursor.getString(bookNameIndex)
                val cursorBookAuthor = cursor.getString(bookAuthorIndex)
                val cursorBorrowedDate = cursor.getString(borrowedDateIndex)
                val borrowedBooksModel = BorrowedBooksModel(cursorBookName,cursorBookAuthor,cursorBorrowedDate)
                arrayList.add(borrowedBooksModel)
                println("ID : $cursorID , Book Name : $cursorBookName , Book Author : $cursorBookAuthor , Borrowed Date : $cursorBorrowedDate")
            }
            cursor.close()
        } catch (e:Exception) {
            e.printStackTrace()
        }

    }


}