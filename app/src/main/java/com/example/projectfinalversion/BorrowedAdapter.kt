package com.example.projectfinalversion

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectfinalversion.databinding.BorrowedRowBinding

class BorrowedAdapter(val context:Context, val arrayList:ArrayList<BorrowedBooksModel>) : RecyclerView.Adapter<BorrowedAdapter.ItemHolder>() {

    class ItemHolder(val binding: BorrowedRowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = BorrowedRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.recyclerBooksName.text = arrayList[position].booksName
        holder.binding.recyclerAuthorName.text = arrayList[position].booksAuthor
        holder.binding.recyclerBorrowedDate.text = "Delivery Date : ${arrayList[position].borrowedDate}"
        when(arrayList[position].booksName) {
            "A Little Life" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.alittlelife)
            }
            "All My Puny Sorrows" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.allmypunysorrows)
            }
            "Hobbit" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.hobbit)
            }
            "Leave the World Behind" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.leavetheworldbehind)
            }
            "Memorial" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.memorial)
            }
            "Milkman" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.milkman)
            }
            "Outline" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.outline)
            }
            "The Idiot" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.theidiot)
            }
            "The Woman Upstairs" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.thewomanupstairs)
            }
            "Trust Exercise" -> {
                holder.binding.recyclerBooksImageView.setImageResource(R.drawable.trustexercise)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


}