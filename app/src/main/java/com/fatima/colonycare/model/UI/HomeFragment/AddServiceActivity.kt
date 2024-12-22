package com.fatima.colonycare.model.UI.HomeFragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.fatima.colonycare.R
import com.fatima.colonycare.databinding.ActivityAddServiceBinding
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import kotlinx.coroutines.launch

class AddServiceActivity : AppCompatActivity() {
    private var uri: Uri? = null
    lateinit var binding: ActivityAddServiceBinding;
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel()

        lifecycleScope.launch {
            viewModel.isSucessfullySaved.collect {
                it?.let {
                    if (it == true) {
                        Toast.makeText(
                            this@AddServiceActivity,
                            "Successfully saved",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        finish()
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.isFailure.collect {
                it?.let {
                    Toast.makeText(this@AddServiceActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.submitButton.setOnClickListener {

//            val heading=binding.headerText.text.toString().trim()
//            val picHeader=binding.uploadText.text.toString().trim()
            val servicename=binding.serviceTitle.text.toString().trim()
            val servicedescription=binding.serviceDescription.text.toString().trim()
            val serviceprice=binding.servicePrice.editText.toString().trim()
            val serviceprovidername=binding.serviceProviderName.editText.toString().trim()
            val serviceproviderrole=binding.serviceProviderRole.editText.toString().trim()

            // Validate the input fields
            if (servicename.isEmpty() || servicedescription.isEmpty() || serviceprice.isEmpty() || serviceprovidername.isEmpty() || serviceproviderrole.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = 300;
//
//            if (price == null) {
//                Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }



            val service= ServicesModelClass()
            service.ServiceName=servicename
            service.ServiceDescription=servicedescription
            service.ServicePrice=price!!
            service.ServiceProviderName=serviceprovidername
            service.ServiceRole=serviceproviderrole

            if (uri == null)
                viewModel.addServices(service)
            else
                viewModel.uploadImageAndSaveServices(getRealPathFromURI(uri!!)!!, service)

            // Save the service object (this would be a database operation, Firestore, etc.)
            // For now, just display the success message
            Toast.makeText(this, "Service Added Successfully!", Toast.LENGTH_SHORT).show()

        }

        binding.image1.setOnClickListener {
            chooseImageFromGallery()
        }

    }

    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            uri = result.data?.data
            if (uri != null) {
                binding.image1.setImageURI(uri)
            } else {
                Log.e("Gallery", "No image selected")
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        }
        return null
    }
}