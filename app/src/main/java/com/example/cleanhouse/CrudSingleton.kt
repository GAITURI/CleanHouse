package com.example.cleanhouse

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import java.lang.reflect.Constructor

class CrudSingleton constructor(context: Context) {
   companion object{
       @volatile
       private var INSTANCE:CrudSingleton? = null

       annotation class volatile

       fun getInstance(context: Context)=
               INSTANCE ?: synchronized(this){
                   INSTANCE ?: CrudSingleton(context).also {
                       INSTANCE =it
                   }
               }

   }

    private  val requestQueue:RequestQueue by lazy {
        //application context is key it kees you from leaking the activity or broadcast receiver if someone passes one in
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req:Request<T>){
        requestQueue.add(req)
    }
}