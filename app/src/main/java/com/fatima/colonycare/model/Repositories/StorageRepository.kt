package com.fatima.colonycare.model.Repositories

import com.fatima.colonycare.model.DataSource.CloudinaryUploadHelper


class StorageRepository {

    fun uploadFile(filePath:String,onComplete: (Boolean,String?) -> Unit){
        CloudinaryUploadHelper().uploadFile(filePath,onComplete)
    }

}