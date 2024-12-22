package com.fatima.colonycare.model.UI.ServicesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fatima.colonycare.databinding.CategoryCardviewBinding
import com.fatima.colonycare.model.UI.ModelClasses.CategoryModelClass

class CategoryAdapter(private val categories: List<CategoryModelClass>) :
    RecyclerView.Adapter<CategoryAdapter.ServiceViewHolder>() {

    inner class ServiceViewHolder(private val binding: CategoryCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryModelClass) {
            binding.categoryIcon.setImageResource(category.CategoryIconId)
            binding.categoryName.text = category.CategoryName
            binding.categoryServicesCount.text = "${category.CategoryCount} Services"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = CategoryCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
