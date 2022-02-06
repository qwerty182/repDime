package com.example.praktika4k1s

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.praktika4k1s.models.ModelClassCabinetAdd
import kotlinx.android.synthetic.main.activity_add_cabinet_admin.*
import kotlinx.android.synthetic.main.activity_report_add.*
import java.io.ByteArrayOutputStream
import java.util.*

class AddCabinetActivityAdmin : AppCompatActivity() {

    var idBuild: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cabinet_admin)

        imageCabinetAdd.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }

        btnSaveCab.setOnClickListener {
            addCabinet()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageCabinetAdd.setImageBitmap(bitmap)
        }
    }

    private fun addCabinet(){

        if (spinnerBuild.selectedItem == "Нахимовский проспект"){
            idBuild = 1
        }else if(spinnerBuild.selectedItem == "Нежинская"){
            idBuild = 2
        }

        if (editCabinetNumberAdd.text.isNullOrEmpty() || editGeneralInfo.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }
        val apiService = RestApiManager()
        val modelClass = ModelClassCabinetAdd(
            cabinetNumber = editCabinetNumberAdd.text.toString(),
            cabinetImage = BitmapToByteArray().toBase64(),
            generalInformation = editGeneralInfo.text.toString(),
            buildingId = idBuild)

        apiService.addCabinets(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Кабинет добавлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AddCabinetActivityAdmin, MainActivityAdmin::class.java)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageCabinetAdd.drawable.toBitmap().compress(Bitmap.CompressFormat.JPEG,
            100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }
    private fun ByteArray.toBase64(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getEncoder().encode(this))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
}