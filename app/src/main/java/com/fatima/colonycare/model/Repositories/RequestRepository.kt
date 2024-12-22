package com.fatima.colonycare.model.Repositories

import com.fatima.colonycare.model.UI.ModelClasses.RequestModelClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RequestRepository {

//    val RequesstCollection = FirebaseFirestore.getInstance().collection("Request")
//
//
//    // to save the document in the firestore
//    suspend fun addRequest(request: RequestModelClass): Result<Boolean> {
//        try {
//            val document = RequesstCollection.document()
//            request.id = document.id
//            document.set(request).await()
//            return Result.success(true)
//        } catch (e: Exception) {
//            return Result.failure(e)
//        }
//    }
//
//    //get(read) the document from the firestore
//    fun getRequest()=RequesstCollection.snapshots().map { it.toObjects(RequestModelClass::class.java) }

}