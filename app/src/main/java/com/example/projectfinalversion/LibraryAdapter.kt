package com.example.projectfinalversion

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectfinalversion.databinding.LibraryRowBinding

class LibraryAdapter(val context:Context,val arrayList: ArrayList<LibraryModel>) : RecyclerView.Adapter<LibraryAdapter.ItemHolder>() {



    class ItemHolder(val binding: LibraryRowBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = LibraryRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.recyclerBooksImageView.setImageResource(arrayList[position].booksImage)
        holder.binding.recyclerBooksName.text = arrayList[position].booksName
        holder.binding.recyclerAuthorName.text = arrayList[position].booksAuthor
        holder.binding.imageView.setOnClickListener {
            val intent = Intent(context,DeliveryDateActivity::class.java)
            intent.putExtra("Books Image",arrayList[position].booksImage)
            intent.putExtra("Books Name",arrayList[position].booksName)
            intent.putExtra("Books Author",arrayList[position].booksAuthor)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


}