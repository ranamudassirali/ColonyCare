package com.fatima.colonycare.model.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatima.colonycare.R
import com.fatima.colonycare.databinding.FragmentHomeBinding
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import com.fatima.colonycare.model.UI.ServicesAdapter.ServiceAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    lateinit var adapter: ServiceAdapter
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeFragmentViewModel
    val items1=ArrayList<ServicesModelClass>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("test1", "Test")
        adapter= ServiceAdapter(items1)
        binding.recyclerview.adapter=adapter
        binding.recyclerview.layoutManager= LinearLayoutManager(context)

        viewModel= HomeFragmentViewModel()
        lifecycleScope.launch {
            viewModel.isfailure.collect {
                it?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.serviceList.collect {
                it?.let {
                    items1.clear()
                    items1.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


}