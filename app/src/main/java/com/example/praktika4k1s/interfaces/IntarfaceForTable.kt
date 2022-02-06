package com.example.praktika4k1s.interfaces

import com.example.praktika4k1s.models.*
import retrofit2.Call
import retrofit2.http.*

//Интерфейсы для вызова Api
interface InterfaceApiAuthorization {
    @POST("AutorizationUser")
    fun userAuto(@Body userDb: ModelClassUser): Call<ModelClassUser>
}

interface InterfaceApiRegistration {
    @POST("RegistrationUser")
    fun userReg(@Body userReg: ModelClassRegistration): Call<ModelClassRegistration>
}

interface InterfaceApiSearch {
    @GET("CabinetList")
    fun CabinetList(@Query ("cabinetNumberDb") cabinetNumberDb: String): Call<List<ModelClassCabinetList>>
}

interface InterfaceApiCabinetItem {
    @GET("InfoCabinetItem")
    fun CabinetItem(@Query ("id") id: Int): Call<ModelClassCabinetItem>
}

interface InterfaceApiReportItem {
    @POST("ReportItem")
    fun addReport(@Body addRep: ModelClassReportItem): Call<ModelClassReportItem>
}

interface InterfaceApiAddCabinet {
    @POST("CabinetAdd")
    fun addCabinet(@Body addCab: ModelClassCabinetAdd): Call<ModelClassCabinetAdd>
}

interface InterfaceApiEditCabinet {
    @POST("CabinetEdit")
    fun editCabinet(@Body editCab: ModelClassCabinetEdit): Call<ModelClassCabinetEdit>
}

interface InterfaceApiReportList {
    @GET("ReportList")
    fun reportList(@Query ("id") id: Int): Call<List<ModelClassReportsList>>
}

interface InterfaceApiReportInfo {
    @GET("InfoReportItem")
    fun reportInfo(@Query ("id") id: Int): Call<ModelClassReportInfo>
}

interface InterfaceApiEditReport {
    @POST("StatusEdit")
    fun editReport(@Body editRep: ModelClassReportsEdit): Call<ModelClassReportsEdit>
}