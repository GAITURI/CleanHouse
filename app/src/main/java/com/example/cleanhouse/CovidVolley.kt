package com.example.cleanhouse

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_covid_volley.*
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class CovidVolley : AppCompatActivity() {

    private var mExampleAdapter:CovidAdapter?=null
    private  var mExampleList:ArrayList<CovidVolleyModel>?=null
    private var mRequestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_volley)

//give our recyclerview a viewGroup
        covidView.layoutManager=LinearLayoutManager(this)
        //set a fixed item size
        covidView.setHasFixedSize(true)
//create an instance of volley
        mExampleList=ArrayList()
        mRequestQueue= Volley.newRequestQueue(this)

//check if network is connected
if (isNetworkConnected()){
    fetchDetails()
}else{
    val errorDialog= SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
    errorDialog.setTitleText("Loading...")
    errorDialog.setCancelable(true)
   errorDialog.show()
}





    }
    private fun fetchDetails(){
        val loadingDialog= SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.setTitleText("Loading...")
        loadingDialog.setCancelable(true)
        loadingDialog.show()
        //HAVE A VARIABLE DEFINING OUR NETWORKING URL
        val url:String="https://api.covid19api.com/summary"
        //use volley to get details from json
        val request= JsonObjectRequest(Request.Method.GET,url,null,Response.Listener{response->
            loadingDialog.dismiss()
            try{
                val jsonArray=response.getJSONArray("Countries")
              //to create a loop to loop over contenet of my array saving the info we need to display on app

                for (i in 0 until jsonArray.length()) {
                    //variable to store iterations
                    val hit = jsonArray.getJSONObject(i)
                    val countryName = hit.getString("Country")
                    val countryNewCases = hit.getInt("NewConfirmed")
                    val countryTotalCases = hit.getInt("TotalConfirmed")
                    val countryNewRec = hit.getInt("NewRecovered")
                    val countryTotalRec = hit.getInt("TotalRecovered")
                    val id = hit.getString("ID")
                    val countryNewDeathCases = hit.getInt("NewDeaths")
                    val countryTotalDeathCases = hit.getInt("TotalDeaths")
                    val countryDate=hit.getString("Date")


                    mExampleList!!.add(CovidVolleyModel(id,countryName,countryNewCases,countryTotalCases,countryNewRec,countryTotalRec,countryNewDeathCases,countryTotalDeathCases,countryDate))
                }
            //add model data to adapter
                mExampleAdapter=mExampleList?.let {CovidAdapter(this,it)}
                covidView!!.adapter = mExampleAdapter

            }catch (e : JSONException){
                e.printStackTrace()}


        },Response.ErrorListener {
            error ->error.printStackTrace()
            Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_LONG).show()
        })
        mRequestQueue!!.add(request)
    }


    private fun isNetworkConnected():Boolean{
        val connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M  ){
            connectivityManager.activeNetwork
        }else{
            TODO("VERSION.SDK_INT<M")
        }
        val networkCapabilities= connectivityManager.getNetworkCapabilities(activeNetwork)
        return  networkCapabilities !=null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


}