package com.example.praktika4k1s

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.praktika4k1s.models.ModelClassCabinetEdit
import kotlinx.android.synthetic.main.activity_add_cabinet_admin.*
import kotlinx.android.synthetic.main.activity_admin_edit_cabinet.*
import java.io.ByteArrayOutputStream
import java.util.*

class AdminEditCabinetActivity : AppCompatActivity() {

    var idCabinetEdit: Int = 0
    var idUserEdit: Int = 0
    var idBuildEdit: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_cabinet)

        idCabinetEdit = intent.getIntExtra("idCabinetEdit", 0)
        idUserEdit = intent.getIntExtra("idUserEdit", 0)

        imageCabinetEdit.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }



        btnSaveCabEdit.setOnClickListener {
            editCabinet()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageCabinetEdit.setImageBitmap(bitmap)
        }
    }

    private fun editCabinet(){

        if (spinnerBuildEdit.selectedItem == "Нахимовский проспект"){
            idBuildEdit = 1
        }else if(spinnerBuildEdit.selectedItem == "Нежинская"){
            idBuildEdit = 2
        }

        if (editCabinetNumberEdit.text.isNullOrEmpty() || editGeneralInfoEdit.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }
        val apiService = RestApiManager()
        val modelClass = ModelClassCabinetEdit(
            idCabinet = idCabinetEdit,
            cabinetNumber = editCabinetNumberEdit.text.toString(),
            cabinetImage = BitmapToByteArray().toBase64(),
            generalInformation = editGeneralInfoEdit.text.toString(),
            buildingId = idBuildEdit)

        apiService.editCabinets(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Кабинет изменен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdminEditCabinetActivity, MainActivityAdmin::class.java)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageCabinetEdit.drawable.toBitmap().compress(Bitmap.CompressFormat.JPEG,
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