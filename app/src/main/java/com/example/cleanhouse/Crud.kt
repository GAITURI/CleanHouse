 package com.example.cleanhouse

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_crud.*
import org.json.JSONObject
import java.lang.Exception

 class Crud : AppCompatActivity() {
    private var  imageUri: Uri?  = null
    //variable to store users input
    var texa:String=""
    var texb:String=""
     var userId="2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        postData.setOnClickListener{
            postToApi()
        }
        deleteData.setOnClickListener{
                deleteUsingApi()
        }
        updateData.setOnClickListener{
                updateUsingApi()
        }
    }

    private fun updateUsingApi() {

        texa=  crudText.text.toString()
        texb=crudText2.text.toString()
        //update using Api
        usingApiUpdate (texa,texb,userId)



    }

     private fun usingApiUpdate(texa: String, texb: String, userId:String) {
         val url= "https://postman-echo.com/put?" + userId
         val params = HashMap<String,String>()
         params["foo1"]=texa
         params["foo2"] =texb
         val jsonObject=JSONObject(params as Map<*,*>)
   val request=JsonObjectRequest(Request.Method.PUT,url,jsonObject,Response.Listener { response ->
       try{
           Log.d("message","Response: $response")
           Toast.makeText(applicationContext,"Successful Update",Toast.LENGTH_LONG).show()
       }catch (e:Exception){
           Log.d("message","Exception:$e")
           Toast.makeText(applicationContext,"Error ",Toast.LENGTH_LONG).show()

       }




   },Response.ErrorListener {
        Log.d("message","Exception:$it")
       Toast.makeText(applicationContext,"Error ",Toast.LENGTH_LONG).show()

   })

//volley request policy, only one time request to avoid duplication
         request.retryPolicy= DefaultRetryPolicy(
                 DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                 //O MEANS NO RETRY
                 0,
                 1f
         )

//adding request to policy
         CrudSingleton.getInstance(this).addToRequestQueue(request)





     }

     private fun deleteUsingApi() {

         var url= "https://postman-echo.com/delete?" +userId
        val request= StringRequest(Request.Method.DELETE, url,
        {response ->
                    try{
                        Log.d("message","Response: $response")
                        Toast.makeText(applicationContext,"Successfully Deleted",Toast.LENGTH_LONG).show()
                    }catch (e:Exception){
                        Log.d("message","Exception:$e")
                    }
                }

        ){
            Log.d("message","VolleyError: $it")
        }
         //volley request policy, only one time request to avoid duplication
         request.retryPolicy= DefaultRetryPolicy(
                 DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                 //O MEANS NO RETRY
                 0,
                 1f
         )

//adding request to policy
         CrudSingleton.getInstance(this).addToRequestQueue(request)

     }





    fun PostData(view: View) {
        //using imagepicker to select image
ImagePicker.with(this)
    .crop()
    .compress(1024)
    .maxResultSize(1080,1080)
    .start()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            //IMAGE URI WILL NOT BE NULL FOR RESULT OK
            imageUri= data?.data!!
            //USE URI OBJECT INSTEAD OF FILE TO AVOID STORAGE PERMISSIONS
            crudImage.setImageURI(imageUri)

        }else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this,ImagePicker.getError(data),Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this,"Task  Cancelled",Toast.LENGTH_SHORT).show()
        }

    }
    private fun postToApi() {

        texa=  crudText.text.toString()
        texb=crudText2.text.toString()
        //postToApi
        submitToApi(texa,texb,imageUri)

    }

    private fun submitToApi(texa: String, texb: String, imageUri: Uri?) {
         
    val url= "https://postman-echo.com/post"
//post parameters
//form fields and values
   val params = HashMap<String,String>()
   params["foo1"]=texa
   params["foo2"] =texb
   val jsonObject=JSONObject(params as Map<*,*>)

val request= JsonObjectRequest(Request.Method.POST,url,jsonObject,Response.Listener { response ->
//capture success
//process the json
    try{
        Log.d("message","Response: $response")
    Toast.makeText(applicationContext,"Successful Submission",Toast.LENGTH_LONG).show()
    }catch (e:Exception){
        Log.d("message","Exception:$e")
    }
},Response.ErrorListener {
   //capture failure
    Log.d("message","Exception:$it")
    Toast.makeText(applicationContext,"Error,check Internet ",Toast.LENGTH_LONG).show()
})
//volley request policy, only one time request to avoid duplication
        request.retryPolicy= DefaultRetryPolicy(
          DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
        //O MEANS NO RETRY
        0,
         1f
        )

//adding request to policy
        CrudSingleton.getInstance(this).addToRequestQueue(request)




    }
}