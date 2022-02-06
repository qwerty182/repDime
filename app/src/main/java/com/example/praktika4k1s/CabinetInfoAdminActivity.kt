package com.example.praktika4k1s

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.praktika4k1s.interfaces.InterfaceApiCabinetItem
import com.example.praktika4k1s.models.ModelClassCabinetItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_admin_report_info.*
import kotlinx.android.synthetic.main.activity_cabinet_info_admin.*
import kotlinx.android.synthetic.main.activity_detail_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CabinetInfoAdminActivity : AppCompatActivity() {

    var idCabinet: Int = 0
    var idUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet_info_admin)

        idCabinet = intent.getIntExtra("idCabinet", 0)
        idUser = intent.getIntExtra("idUser", 0)

        Log.i("MyLog", idCabinet.toString())

        btnEditCabinet.setOnClickListener {
            val intent = Intent(this@CabinetInfoAdminActivity, AdminEditCabinetActivity::class.java)
            intent.putExtra("idCabinetEdit", idCabinet)
            intent.putExtra("idUserEdit", idUser)
            startActivity(intent)
        }

        btnViewReport.setOnClickListener {
            val intent = Intent(this@CabinetInfoAdminActivity, AdminViewReportsActivity::class.java)
            intent.putExtra("idCabinetReport", idCabinet)
            intent.putExtra("idUserReport", idUser)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://gopher-server.xyz:49155/api/Info/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(InterfaceApiCabinetItem::class.java)
        val call = service.CabinetItem(id = idCabinet)
        call.enqueue(object : Callback<ModelClassCabinetItem> {
            override fun onResponse(call: Call<ModelClassCabinetItem>, response: Response<ModelClassCabinetItem>) {
                if (response.code() == 200) {
                    val detail = response.body()!!
                    textCabinetNumAd?.text = detail.cabinetNumber
                    textGeneralInfoAd?.text = detail.cabinetInfo

                    val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Base64.getDecoder().decode(detail.cabinetImage)
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                    imageCabinetAd!!.setImageBitmap(
                        BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

                    textBuildAd?.text = detail.cabinetBuild
                }
            }
            override fun onFailure(call: Call<ModelClassCabinetItem>, t: Throwable) {

            }
        })
    }
}