package com.example.cleanhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class CollectData : AppCompatActivity() {

    lateinit var editText:EditText
    lateinit var editText1:EditText
    lateinit var editText2:EditText
    lateinit var editText3:EditText
    lateinit var editText4:EditText
    lateinit var editText5:EditText
    lateinit var editText6:EditText
    lateinit var  textView:TextView
    lateinit var button: Button



//initialise all variables
private   var names:String=""
private var genders:String=""
private var housenumbers:Int=0
private var phones:Int=0
private var emails:String=""
private var pests:String=""
private var dates:String=""







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view identification
        editText = findViewById(R.id.ED1)
        editText1 = findViewById(R.id.ED2)
        editText2 = findViewById(R.id.ED3)
        editText3 = findViewById(R.id.ED4)
        editText4 = findViewById(R.id.ED5)
        editText5 = findViewById(R.id.ED6)
        editText6 = findViewById(R.id.ED7)
        button = findViewById(R.id.btn)



    }
        //fetch the text
        fun onSaveButtonClick(view: View) {

         val name= ED1.text.toString ()
         val gender= ED2.text.toString ()
        val housenumber= ED3.text.toString ()
        val phone= ED4.text.toString ()
        val email= ED5.text.toString ()
       val pest= ED6.text.toString ()
       val date= ED7.text.toString ()


       //fetch text from names
    names =editText.text.toString()
            Log.d("ShareData","text from Edit text1" + editText + "text from Edit text" + names)


            genders = editText1.text.toString()
            Log.d("ShareData","text from Edit text2" + editText1 + "text from Edit text" + genders)


            housenumbers = editText2.text.toString().toInt()
            Log.d("ShareData","text from Edit text1" + editText2 + "text from Edit text" + housenumbers)

            phones = editText3.text.toString().toInt()
            Log.d("ShareData","text from Edit text1" + editText3 + "text from Edit text" + phones)

            emails = editText4.text.toString()
            Log.d("ShareData","text from Edit text1" + editText4 + "text from Edit text" + emails)

            pests = editText5.text.toString()
            Log.d("ShareData","text from Edit text1" + editText5 + "text from Edit text" + pests)

            dates= editText6.text.toString()
            Log.d("ShareData","text from Edit text1" + editText6 + "text from Edit text" + dates)


            val intentFormSharing = Intent(this@CollectData,ConfirmData::class.java)

            //intent to sharing text
            intentFormSharing.putExtra("Name", names)
            intentFormSharing.putExtra("Gender",genders)
            intentFormSharing.putExtra("House NO", housenumbers)
            intentFormSharing.putExtra("Phone No", phones)
            intentFormSharing.putExtra("Email add",emails)
            intentFormSharing.putExtra("Pest to control",pests)
            intentFormSharing.putExtra("Date",dates)
            startActivity(intentFormSharing)





        }






    }


