package com.example.projectfinalversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectfinalversion.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {


    private lateinit var binding : FragmentLibraryBinding
    private lateinit var arrayList : ArrayList<LibraryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        arrayList = ArrayList()
        arrayList.add(LibraryModel(R.drawable.alittlelife,"A Little Life","Hanya Yanagihara"))
        arrayList.add(LibraryModel(R.drawable.allmypunysorrows,"All My Puny Sorrows","Miriam Toews"))
        arrayList.add(LibraryModel(R.drawable.hobbit,"Hobbit","J.R.R. Tolkien"))
        arrayList.add(LibraryModel(R.drawable.leavetheworldbehind,"Leave the World Behind","Rumaan Alam"))
        arrayList.add(LibraryModel(R.drawable.memorial,"Memorial","Bryan Washington"))
        arrayList.add(LibraryModel(R.drawable.milkman,"Milkman","Anna Burns"))
        arrayList.add(LibraryModel(R.drawable.outline,"Outline","Rachel Cusk"))
        arrayList.add(LibraryModel(R.drawable.theidiot,"The Idiot","Elif Batuman"))
        arrayList.add(LibraryModel(R.drawable.thewomanupstairs,"The Woman Upstairs","Claire Messud"))
        arrayList.add(LibraryModel(R.drawable.trustexercise,"Trust Exercise","Susan Choi"))
        with(binding) {
            libraryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val libraryAdapter = LibraryAdapter(requireContext(),arrayList)
            libraryRecyclerView.adapter = libraryAdapter
        }


        return binding.root
    }


}