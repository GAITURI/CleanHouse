package com.example.cleanhouse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.covid_item.view.*

class CovidAdapter(private var context: Context,private val itemList:List<CovidVolleyModel>):RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {
//attach
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidAdapter.CovidViewHolder {
       val inflater =LayoutInflater.from(parent.context).inflate(R.layout.covid_item,parent,false)
    return CovidViewHolder(inflater)
    }
//count
    override fun getItemCount() =itemList.size
//bind
    override fun onBindViewHolder(holder: CovidAdapter.CovidViewHolder, position: Int) {
    val items=itemList[position]
    holder.countryId.text="CountryId: "+items.id
   holder.countryName.text="CountryName: "+items.countryName_m
   holder.countryNewCases.text="NewCases: "+items.newCases_m.toString()
    holder.countryTotalCases.text="TotalCases: "+items.totalCases_m.toString()
    holder.countryNewRecoveries.text="NewRecoveries: "+items.newRecoveries_m.toString()
    holder.countryTotalRecoveries.text="TotalRecoveries: "+items.totalRecoveries_m.toString()

    holder.countryNewDeathCases.text="NewDeathCases: "+items.newDeathCases_m.toString()
    holder.countryTotalDeathCases.text="TotalDeathCases: "+items.totalDeathCases.toString()
    holder.countryDate.text="Date: "+items.date
}



    class CovidViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//kotlin extensions to reference the ids
        val countryName :TextView=itemView.text1
        val countryNewCases :TextView=itemView.text2
        val countryTotalCases :TextView=itemView.text3
        val countryNewRecoveries :TextView=itemView.text4
        val countryTotalRecoveries :TextView=itemView.text5

        val countryNewDeathCases:TextView=itemView.text7
        val countryTotalDeathCases:TextView=itemView.text8
        val countryDate :TextView=itemView.text9
        val countryId:TextView=itemView.text6

    }


}