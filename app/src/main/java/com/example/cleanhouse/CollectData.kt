package com.example.cleanhouse

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

            if (id.trim()!="" && name.trim()!="" && gender.trim()!="" && phone.trim()!="" && email.trim()!="" && pest.trim()!="" && date.trim()!="") {
                val status = databaseHandler.addUsers(SqlListModel(name,Integer.parseInt(id),gender,Integer.parseInt(phone),email, pest,Integer.parseInt(date)))
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


    fun deleteData(view: View) {
        //create an alertDialogue for taking user id
        val dialogBuilder= AlertDialog.Builder(this)
        val inflater= this.layoutInflater
        //attach our custom view to the pop up
        val dialogView=inflater.inflate(R.layout.delete,null)
        dialogBuilder.setView(dialogView)



        //capture the editText
        val delete_Id=dialogView.findViewById<EditText>(R.id.deleteId)
        //customise our pop up :tittle and message
        dialogBuilder.setTitle("DeleteData")
        dialogBuilder.setMessage("Enter id to delete data")
        //set up our buttons
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->

            //set what happens when the positive button is clicked
            //capture user input
            val inputId=delete_Id.text.toString()
            //create an instance of the database class
            val databaseHandler= DatabaseHandler(this)
            //validate that the input variable actually has data in it
            if (inputId.trim()!=""){
                //here we will use our delete method
                val status= databaseHandler.deleteData(SqlListModel("",Integer.valueOf(inputId),"",1,"","",2))
                if (status > -1){
                    Toast.makeText(applicationContext,"Record deleted successfully",Toast.LENGTH_LONG).show()
                }


            }else{
                Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()
            }
        })


        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //set what happens when negative button is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })

        dialogBuilder.setNeutralButton("Help", DialogInterface.OnClickListener   { dialog, which ->
            Toast.makeText(applicationContext,"Input an id to delete",Toast.LENGTH_LONG).show()


        })
        //t
        val b=dialogBuilder.create()
        b.show()

    }

   fun updateData(view: View) {
        //create an alert dialogue
        val dialogBuilder=AlertDialog.Builder(this)
        val inflater= this.layoutInflater
        //attach our custom view to the pop up
        val dialogView=inflater.inflate(R.layout.update_dialogue,null)
        dialogBuilder.setView(dialogView)
        //reference to the views
        val update_id=dialogView.findViewById<EditText>(R.id.updateId)
        val update_name=dialogView.findViewById<EditText>(R.id.updateUsername)
        val update_Phone=dialogView.findViewById<EditText>(R.id.updatePhoneNumber)
       val update_Gender=dialogView.findViewById<EditText>(R.id.updateGender)
       val update_pest=dialogView.findViewById<EditText>(R.id.updatePest)
       val update_Date=dialogView.findViewById<EditText>(R.id.updatedate)
       val update_email=dialogView.findViewById<EditText>(R.id.updateUserEmail)




        //customize the pop up
        dialogBuilder.setTitle("Update data")
        dialogBuilder.setMessage("Enter an id, to update specific record")

        dialogBuilder.setPositiveButton("Update data",DialogInterface.OnClickListener {dialog, which ->
            val  updateName=update_name.text.toString()
            val  updateGender=update_Gender.text.toString()
            val  updatePhone=update_Phone.text.toString()
            val  updateDate=update_Date.text.toString()
            val  updatePest=update_pest.text.toString()
            val  updateEmail=update_email.text.toString()
            val updateId=update_id.text.toString()



            //validate data
            if (updateId.trim() !="" &&updateName.trim() !="" &&updateEmail.trim()!="" &&updateGender.trim() !=""  &&updatePhone.trim()!="" &&updateDate.trim() !="" &&updatePest.trim()!="" )       {
                //  update record
                //instance of the database handler
                val databaseHandler= DatabaseHandler(this)
                val status= databaseHandler.updateData(SqlListModel(updateName,Integer.valueOf(updateId),updateGender,Integer.valueOf(updatePhone),updateEmail,updatePest,Integer.valueOf(updateDate)))
                if (status>-1){
                    Toast.makeText(applicationContext,"Update successful",Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(applicationContext,"Update failed",Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()
            }

        })

        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
            //set what happens when negative button is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })

        dialogBuilder.setNeutralButton("Help",DialogInterface.OnClickListener   { dialog, which ->
            Toast.makeText(applicationContext,"Input an id to update",Toast.LENGTH_LONG).show()


        })
        val b=dialogBuilder.create()
        b.show()


    }




    }







