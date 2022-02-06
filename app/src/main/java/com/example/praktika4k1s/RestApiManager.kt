package com.example.praktika4k1s

import android.util.Log
import com.example.praktika4k1s.interfaces.*
import com.example.praktika4k1s.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RestApiManager {
    fun userAuthorization(userDb: ModelClassUser, onResult: (ModelClassUser?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiAuthorization::class.java)
        retrofit.userAuto(userDb).enqueue(
            object : Callback<ModelClassUser> {
                override fun onFailure(call: Call<ModelClassUser>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassUser>, response: Response<ModelClassUser>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun userRegistration(userReg: ModelClassRegistration, onResult: (ModelClassRegistration?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiRegistration::class.java)
        retrofit.userReg(userReg).enqueue(
            object : Callback<ModelClassRegistration> {
                override fun onFailure(call: Call<ModelClassRegistration>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassRegistration>, response: Response<ModelClassRegistration>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun addReports(addRep: ModelClassReportItem, onResult: (ModelClassReportItem?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiReportItem::class.java)
        retrofit.addReport(addRep).enqueue(
            object : Callback<ModelClassReportItem> {
                override fun onFailure(call: Call<ModelClassReportItem>, t: Throwable) {
                    Log.i("MyLog", "drop")
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassReportItem>, response: Response<ModelClassReportItem>) {
                    Log.i("MyLog", response.message() + response.code() + response.errorBody())
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun addCabinets(addCab: ModelClassCabinetAdd, onResult: (ModelClassCabinetAdd?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiAddCabinet::class.java)
        retrofit.addCabinet(addCab).enqueue(
            object : Callback<ModelClassCabinetAdd> {
                override fun onFailure(call: Call<ModelClassCabinetAdd>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassCabinetAdd>, response: Response<ModelClassCabinetAdd>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editCabinets(editCab: ModelClassCabinetEdit, onResult: (ModelClassCabinetEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiEditCabinet::class.java)
        retrofit.editCabinet(editCab).enqueue(
            object : Callback<ModelClassCabinetEdit> {
                override fun onFailure(call: Call<ModelClassCabinetEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassCabinetEdit>, response: Response<ModelClassCabinetEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editReports(editRep: ModelClassReportsEdit, onResult: (ModelClassReportsEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiEditReport::class.java)
        retrofit.editReport(editRep).enqueue(
            object : Callback<ModelClassReportsEdit> {
                override fun onFailure(call: Call<ModelClassReportsEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ModelClassReportsEdit>, response: Response<ModelClassReportsEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}