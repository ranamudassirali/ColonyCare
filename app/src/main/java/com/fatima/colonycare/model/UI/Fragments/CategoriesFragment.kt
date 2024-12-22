package com.fatima.colonycare.model.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatima.colonycare.R
import com.fatima.colonycare.databinding.FragmentCategoriesBinding
import com.fatima.colonycare.model.UI.ModelClasses.CategoryModelClass
import com.fatima.colonycare.model.UI.ServicesAdapter.CategoryAdapter

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf(
            CategoryModelClass(R.drawable.drill_machine, "Carpenter", 6),
            CategoryModelClass(R.drawable.house_cleaning, "Cleaner", 5),
            CategoryModelClass(R.drawable.paint_roller, "Painter", 2),
            CategoryModelClass(R.drawable.electrician_tools, "Electrician", 1),
            CategoryModelClass(R.drawable.air_conditioner, "AC Repair", 3),
            CategoryModelClass(R.drawable.plumber, "Plumber", 2),
            CategoryModelClass(R.drawable.salon, "Men's Salon", 5),
            CategoryModelClass(R.drawable.tv_repair, "TV Repair", 5),
            CategoryModelClass(R.drawable.window_cleaner, "Glass Cleaning", 5),
        )

        val adapter = CategoryAdapter(categories)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
