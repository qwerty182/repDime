package com.example.praktika4k1s

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.praktika4k1s.interfaces.InterfaceApiCabinetItem
import com.example.praktika4k1s.interfaces.InterfaceApiReportInfo
import com.example.praktika4k1s.interfaces.InterfaceApiReportItem
import com.example.praktika4k1s.models.ModelClassCabinetItem
import com.example.praktika4k1s.models.ModelClassReportInfo
import com.example.praktika4k1s.models.ModelClassReportItem
import com.example.praktika4k1s.models.ModelClassReportsEdit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_admin_report_info.*
import kotlinx.android.synthetic.main.activity_detail_cabinet.*
import kotlinx.android.synthetic.main.activity_report_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class AdminReportInfo : AppCompatActivity() {

    var idReport: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_report_info)

        idReport = intent.getIntExtra("idReport", 0)

        Log.i("MyLog", idReport.toString())

        btnStatus.setOnClickListener {
            editReport()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://gopher-server.xyz:49155/api/Info/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(InterfaceApiReportInfo::class.java)
        val call = service.reportInfo(id = idReport)
        call.enqueue(object : Callback<ModelClassReportInfo> {
            override fun onResponse(call: Call<ModelClassReportInfo>, response: Response<ModelClassReportInfo>) {
                if (response.code() == 200) {
                    val report = response.body()!!
                    textLoginInfo?.text = report.userLogin
                    textTypeReportInfo?.text = report.nameType

                    val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Base64.getDecoder().decode(report.imageReport)
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                    imageReportInfo!!.setImageBitmap(
                        BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

                    textCommentInfo?.text = report.commentReport
                    textDateInfo?.text = report.dateReport
                    textCabinetInfo?.text = report.cabinetName
                }
            }
            override fun onFailure(call: Call<ModelClassReportInfo>, t: Throwable) {

            }
        })
    }

    private fun editReport(){

        val apiService = RestApiManager()
        val modelClass = ModelClassReportsEdit(
            idReport = idReport,
            status = true)

        apiService.editReports(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Отчет иправлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdminReportInfo, MainActivityAdmin::class.java)
                startActivity(intent)
            }
        }
    }
}