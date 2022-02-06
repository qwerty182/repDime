package com.example.praktika4k1s.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ModelClassUser(
    @SerializedName("idUser") var idUser : Int?,
    @SerializedName("roleId") var roleId : Int?,
    @SerializedName("login") var login : String?,
    @SerializedName("password") var password : String?
)

data class ModelClassRegistration(
    @SerializedName("login") var login : String?,
    @SerializedName("password") var password : String?,
    @SerializedName("lastName") var lastName : String?,
    @SerializedName("firstName") var firstName : String?,
    @SerializedName("middleName") var middleName : String?,
    @SerializedName("roleId") var roleId : Int?
)

data class ModelClassCabinetList(
    @SerializedName("id") var id : Int,
    @SerializedName("image") var image : String,
    @SerializedName("number") var number : String,
    @SerializedName("build") var build : String
)

data class ModelClassCabinetItem(
    @SerializedName("idCabineta") var idCabineta : Int,
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("cabinetInfo") var cabinetInfo : String,
    @SerializedName("cabinetBuidlId") var cabinetBuidlId : Int,
    @SerializedName("cabinetBuild") var cabinetBuild : String,
)

data class ModelClassReportItem(
    @SerializedName("comment") var comment : String,
    @SerializedName("images") var images : String,
    @SerializedName("dateOfLocations") var dateOfLocations : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("userId") var userId : Int,
    @SerializedName("cabinetId") var cabinetId : Int,
    @SerializedName("reportTypeId") var reportTypeId : Int
)

data class ModelClassCabinetAdd(
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("generalInformation") var generalInformation : String,
    @SerializedName("buildingId") var buildingId : Int
)

data class ModelClassCabinetEdit(
    @SerializedName("idCabinet") var idCabinet : Int,
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("generalInformation") var generalInformation : String,
    @SerializedName("buildingId") var buildingId : Int
)

data class ModelClassReportsList(
    @SerializedName("id") var id : Int,
    @SerializedName("dateReport") var dateReport : String,
    @SerializedName("comment") var comment : String,
    @SerializedName("imageReport") var imageReport : String,
    @SerializedName("status") var status : Boolean
)

data class ModelClassReportInfo(
    @SerializedName("idReport") var idReport : Int,
    @SerializedName("commentReport") var commentReport : String,
    @SerializedName("imageReport") var imageReport : String,
    @SerializedName("dateReport") var dateReport : String,
    @SerializedName("statusReport") var statusReport : Boolean,
    @SerializedName("reportTypeId") var reportTypeId : Int,
    @SerializedName("nameType") var nameType : String,
    @SerializedName("cabinetId") var cabinetId : Int,
    @SerializedName("cabinetName") var cabinetName : String,
    @SerializedName("userId") var userId : Int,
    @SerializedName("userLogin") var userLogin : String
)

data class ModelClassReportsEdit(
    @SerializedName("idReport") var idReport : Int,
    @SerializedName("status") var status : Boolean
)