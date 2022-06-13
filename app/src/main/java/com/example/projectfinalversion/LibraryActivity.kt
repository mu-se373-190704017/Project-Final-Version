package com.example.projectfinalversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projectfinalversion.databinding.ActivityLibraryBinding

class LibraryActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val libraryFragment = LibraryFragment()
        getFragment(libraryFragment)

        with(binding) {

            bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.libraryButton -> {
                        getFragment(libraryFragment)
                    }
                    R.id.borrowedBooksButton -> {
                        val borrowedBooksFragment = BorrowedBooksFragment()
                        getFragment(borrowedBooksFragment)
                    }
                }
                return@setOnItemSelectedListener true
            }

        }
    }

    private fun getFragment(fragment:Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit()
    }


}