package com.example.proba.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.R
//import com.example.proba.R
import com.example.proba.databinding.ListItemBinding
import com.example.proba.retrofit.Task
//import okhttp3.internal.concurrent.Task

class ProductAdapter :ListAdapter<Task, ProductAdapter.Holeder>(Comporator()){

    class Holeder(view:View) : RecyclerView.ViewHolder(view){
        private val binding  = ListItemBinding.bind(view)

        fun bind(product: Task) = with(binding){
            titleDescription.text = product.description //заполняем textview
            description.text = product.description
        }
    }


    class Comporator: DiffUtil.ItemCallback<Task> () { // сравниваем продукты

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holeder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holeder(view)
    }

    override fun onBindViewHolder(holder: Holeder, position: Int) {
        holder.bind(getItem(position ))
    }
}