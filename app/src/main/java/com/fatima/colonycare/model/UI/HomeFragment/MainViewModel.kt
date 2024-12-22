package com.fatima.colonycare.model.UI.HomeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatima.colonycare.model.Repositories.AuthRepository
import com.fatima.colonycare.model.Repositories.RequestRepository
import com.fatima.colonycare.model.Repositories.ServicesRepository
import com.fatima.colonycare.model.Repositories.StorageRepository
import com.fatima.colonycare.model.UI.ModelClasses.RequestModelClass
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    val serviceRepository = ServicesRepository()
    val authRepository= AuthRepository()
    val storageRepository= StorageRepository()

    fun uploadImageAndSaveServices(imagePath: String, services: ServicesModelClass) {
        storageRepository.uploadFile(imagePath, onComplete = { success, result ->
            if (success) {
                services.Serviceimage=result!!
                addServices(services)
            }
            else isFailure.value=result
        })
    }

    init {
        getServices()
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
            currentUser.value=null
        }

    }

    val isSucessfullySaved= MutableStateFlow<Boolean?>(null)
    val currentUser= MutableStateFlow<FirebaseUser?>(null)
    val isFailure= MutableStateFlow<String?>(null)
    val serviceList= MutableStateFlow<List<ServicesModelClass>?>(null)


    //save the data calling the function of repository
        fun addServices(services: ServicesModelClass) {
        viewModelScope.launch {
            val result = serviceRepository.addServices(services)
            if (result.isSuccess) {
                isSucessfullySaved.value = true
            } else {
                isFailure.value = result.exceptionOrNull()?.message

            }

        }

    }

//    read the document from the firestore
    fun getServices() {
        viewModelScope.launch {
            serviceRepository.getServices().catch {
                isFailure.value = it.message
            }
                .collect {
                    serviceList.value = it

                }
        }
    }

}