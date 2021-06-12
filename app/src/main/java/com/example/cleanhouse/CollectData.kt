package com.example.cleanhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class CollectData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       lateinit var editText: EditText

    }

        fun saveData(view: View) {

            val name = ED1.text.toString()
            val gender = ED2.text.toString()
            val id = ED3.text.toString()
            val phone = ED4.text.toString()
            val email = ED5.text.toString()
            val pest = ED6.text.toString()
            val date = ED7.text.toString()

            val databaseHandler:DatabaseHandler=DatabaseHandler(this)

            if (id.trim()!="" && name.trim()!="" && gender.trim()!="" &&phone.trim()!="" &&email.trim()!="" && pest.trim()!="" && date.trim()!="") {
                val status = databaseHandler.addUsers(SqlListModel(name, Integer.parseInt(id), gender, Integer.parseInt(phone), email, pest, Integer.parseInt(date)))
                if (status>-1) {
                    Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                    ED1.text?.clear()
                    ED2.text?.clear()
                    ED3.text?.clear()
                    ED4.text?.clear()
                    ED5.text?.clear()
                    ED6.text?.clear()
                    ED7.text?.clear()
                }

            }else{
//                /if its null,notify the user
                Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()

        }

        }



    fun readData(view:View){
        val  databaseHandler:DatabaseHandler=DatabaseHandler(this)
        val viewData:List<SqlListModel> = databaseHandler.readData()

        val userId=Array<String>(viewData.size){"0"}
        val userName=Array<String>(viewData.size){"null"}
        val userGender=Array<String>(viewData.size){"null"}
        val userPhone=Array<String>(viewData.size){"null"}
        val userEmail=Array<String>(viewData.size){"null"}
        val pest=Array<String>(viewData.size){"null"}
        val date=Array<String>(viewData.size){"null"}


        var index= 0

        for (e in viewData){
            userId[index]=e.userId.toString()
            userName[index]=e.userName
            userGender[index]=e.userGender
            userPhone[index]=e.userPhone.toString()
            userEmail[index]=e.userEmail
            pest[index]=e.pest
            date[index]=e.date.toString()
            index++
        }
        val myAdapter= SqlModel(this,userName,userGender,userId,userPhone,userEmail,pest,date)
        list1.adapter=myAdapter

    }

    }







