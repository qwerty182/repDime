package com.example.praktika4k1s

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktika4k1s.interfaces.InterfaceApiSearch
import com.example.praktika4k1s.models.ModelClassCabinetList
import kotlinx.android.synthetic.main.activity_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CabinetActivity : AppCompatActivity() {

    var idUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet)

        idUser = intent.getIntExtra("idUser", 0)
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("NAME", "")
        if (name != ""){
            val retrofit = Retrofit.Builder()
                .baseUrl("http://gopher-server.xyz:49155/api/Info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(InterfaceApiSearch::class.java)
            val call = service.CabinetList(cabinetNumberDb = name.toString())
            call.enqueue(object : Callback<List<ModelClassCabinetList>> {
                override fun onResponse(call: Call<List<ModelClassCabinetList>>, response: Response<List<ModelClassCabinetList>>) {
                    if (response.code() == 200) {
                        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                        recyclerView.layoutManager = linearLayoutManager
                        recyclerView.adapter = CustomRecyclerAdapter(response.body()!!, this@CabinetActivity)
                    }
                }
                override fun onFailure(call: Call<List<ModelClassCabinetList>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Ничего не найдено", Toast.LENGTH_LONG).show()
                }
            })
        }
        btnSearch.setOnClickListener{
            if (!editSearch!!.text.isNullOrEmpty()){
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://gopher-server.xyz:49155/api/Info/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(InterfaceApiSearch::class.java)
                val call = service.CabinetList(cabinetNumberDb = editSearch.text.toString())
                call.enqueue(object : Callback<List<ModelClassCabinetList>> {
                    override fun onResponse(call: Call<List<ModelClassCabinetList>>, response: Response<List<ModelClassCabinetList>>) {
                        if (response.code() == 200) {
                            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                            recyclerView.layoutManager = linearLayoutManager
                            recyclerView.adapter = CustomRecyclerAdapter(response.body()!!, this@CabinetActivity)
                            val name = editSearch.text.toString().trim()
                            val editor = sharedPreferences.edit()
                            editor.putString("NAME", name)
                            editor.apply()
                            Toast.makeText(applicationContext, "Поиск...", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<List<ModelClassCabinetList>>, t: Throwable) {
                        Toast.makeText(applicationContext, "Api недоступна", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else{
                Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_LONG).show()
            }
        }
    }
}