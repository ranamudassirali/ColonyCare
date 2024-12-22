package com.fatima.colonycare.model.UI.Fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatima.colonycare.model.Repositories.ServicesRepository
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    val serviceRepository = ServicesRepository()

    val isfailure = MutableStateFlow<String?>(null)
    val serviceList = MutableStateFlow<List<ServicesModelClass>?>(null)

    init {
        getServices()
    }

    fun getServices() {
        viewModelScope.launch {
            serviceRepository.getServices().catch {
                isfailure.value = it.message
            }
                .collect {
                    serviceList.value = it
                }
        }
    }
}