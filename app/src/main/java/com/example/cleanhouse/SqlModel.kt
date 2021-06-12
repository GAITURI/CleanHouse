package com.example.cleanhouse

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SqlModel (private val context:Activity,private  val userName:Array<String>,private  val userGender:Array<String>,private  val userId:Array<String>,private  val userPhone:Array<String>,private  val userEmail:Array<String>,private  val pest:Array<String>,private  val date:Array<String>):
ArrayAdapter<String>(context,R.layout.sql_list,userName)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.layoutInflater
        val rowView=inflater.inflate(R.layout.sql_list,null,true)

        val textId:TextView=rowView.findViewById(R.id.userId)
        val textUserName:TextView=rowView.findViewById(R.id.userName)
        val textGender:TextView=rowView.findViewById(R.id.userGender)
        val textPhone:TextView=rowView.findViewById(R.id.userPhone)
        val textEmail:TextView=rowView.findViewById(R.id.userEmail)
        val textPest:TextView=rowView.findViewById(R.id.listPest)
        val textDate:TextView=rowView.findViewById(R.id.userDate)



        textId.text="ID:   ${userId[position]} "
        textUserName.text="ID:   ${userName[position]} "
        textGender.text="ID:   ${userGender[position]} "
        textPhone.text="ID:   ${userPhone[position]} "
        textEmail.text="ID:   ${userEmail[position]} "
        textPest.text="ID:   ${pest[position]} "
        textDate.text="ID:   ${date[position]} "




        return rowView
    }


}
