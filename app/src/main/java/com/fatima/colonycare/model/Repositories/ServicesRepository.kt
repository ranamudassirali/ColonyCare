package com.fatima.colonycare.model.Repositories

import com.fatima.colonycare.model.UI.ModelClasses.RequestModelClass
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class ServicesRepository {

    val ServicesCollection = FirebaseFirestore.getInstance().collection("Services")


    // to save the document in the firestore
    suspend fun addServices(service: ServicesModelClass): Result<Boolean> {
        try {
            val document = ServicesCollection.document()
            service.Sid = document.id
            document.set(service).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    //get(read) the document from the firestore
    fun getServices()=ServicesCollection.snapshots().map { it.toObjects(ServicesModelClass::class.java) }

}