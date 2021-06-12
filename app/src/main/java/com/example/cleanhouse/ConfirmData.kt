package com.example.cleanhouse

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_confrim_data.*


class ConfirmData : AppCompatActivity() {
   lateinit var buttonClick :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confrim_data)

        //find view by id
        buttonClick=findViewById(R.id.btnd)



        val bundle: Bundle? = intent.extras

        val sharedName:String?=intent.getStringExtra("Name")
        val sharedGender:String?= intent.getStringExtra("Gender")
        val sharedHouse:String?=intent.getStringExtra("House NO")
        val sharedPhone:String?=intent.getStringExtra("Phone No")
        val sharedEmail:String?=intent.getStringExtra("Email add")
        val sharedPest:String?=intent.getStringExtra("Pest to control")
        val sharedDate:String? = intent.getStringExtra("Date")

            text1.append("Your name is" + sharedName)
            text2.append("Your Gender is"  + sharedGender)
            text3.append("Your phone No is " + sharedPhone)
            text4.append("Your email is" + sharedPhone)
            text5.append("The pest issue is" + sharedPest)
            text6.append("Date for fumigation would be" + sharedDate)



        buttonClick.setOnClickListener {
            val intentdata=Intent(this,CollectData::class.java)
            startActivity(intentdata)
        }

    }
}