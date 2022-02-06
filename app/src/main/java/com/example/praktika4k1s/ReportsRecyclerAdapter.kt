package com.example.praktika4k1s

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.praktika4k1s.models.ModelClassReportsList
import com.squareup.picasso.Picasso
import java.util.*

class ReportsRecyclerAdapter (private val report: List<ModelClassReportsList>?, private val activity: AdminViewReportsActivity) : RecyclerView.Adapter<ReportsRecyclerAdapter.MyViewHolder>() {

    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var reportCom: TextView? = null
        var reportDate: TextView? = null
        var reportIma: ImageView? = null

        init {
            reportIma = itemView.findViewById(R.id.fotoImageReports)
            reportDate = itemView.findViewById(R.id.textDate)
            reportCom = itemView.findViewById(R.id.textComment)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_reports, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.reportCom?.text = report!![position].comment
        holder.reportDate?.text = report!![position].dateReport

        val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(report[position].imageReport)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        holder.reportIma!!.setImageBitmap(
            BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

        holder.reportCom?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", report[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return report!!.size
    }

}