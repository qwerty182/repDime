package com.example.praktika4k1s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktika4k1s.interfaces.InterfaceApiCabinetItem
import com.example.praktika4k1s.interfaces.InterfaceApiReportList
import com.example.praktika4k1s.interfaces.InterfaceApiSearch
import com.example.praktika4k1s.models.ModelClassCabinetItem
import com.example.praktika4k1s.models.ModelClassCabinetList
import com.example.praktika4k1s.models.ModelClassReportsList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cabinet.*
import kotlinx.android.synthetic.main.activity_detail_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminViewReportsActivity : AppCompatActivity() {

    var idCabinetReport: Int = 0
    var idUserReport: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view_reports)

        idCabinetReport = intent.getIntExtra("idCabinetReport", 0)
        idUserReport = intent.getIntExtra("idUserReport", 0)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://gopher-server.xyz:49155/api/Info/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(InterfaceApiReportList::class.java)
        val call = service.reportList(id = idCabinetReport)
        call.enqueue(object : Callback<List<ModelClassReportsList>> {
            override fun onResponse(call: Call<List<ModelClassReportsList>>, response: Response<List<ModelClassReportsList>>) {
                if (response.code() == 200) {
                    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.adapter = ReportsRecyclerAdapter(response.body()!!, this@AdminViewReportsActivity)
                }
            }
            override fun onFailure(call: Call<List<ModelClassReportsList>>, t: Throwable) {
                Toast.makeText(applicationContext, "Api недоступна", Toast.LENGTH_LONG).show()
            }
        })
    }
}