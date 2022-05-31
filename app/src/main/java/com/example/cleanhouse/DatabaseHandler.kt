package com.example.cleanhouse

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
   companion object{
       private val DATABASE_VERSION=1
       private  val DATABASE_NAME="Pest"
       private val CLIENT_TABLE="Customer"
       private val KEY_ID="Id"
       private  val KEY_NAME="UserName"
       private  val KEY_GENDER="UserGender"
       private  val KEY_PHONE="UserPhone"
       private  val KEY_EMAIL="UserEmail"
       private  val KEY_PEST="Pest"
       private  val KEY_DATE="Date"


   }




    override fun onCreate(db: SQLiteDatabase?) {


   val CREATE_CUSTOMER=("CREATE TABLE " + CLIENT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_DATE + " INTEGER," + KEY_PEST + " TEXT," + KEY_GENDER + " TEXT," + KEY_PHONE + " INTEGER" + ")" )
        db?.execSQL(CREATE_CUSTOMER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + CLIENT_TABLE)
        onCreate(db)
    }

    fun addUsers(sqlListModel:SqlListModel):Long{
        val db = this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(KEY_ID, sqlListModel.userId)
        contentValues.put(KEY_NAME, sqlListModel.userName)
        contentValues.put(KEY_GENDER, sqlListModel.userGender)
        contentValues.put(KEY_PHONE, sqlListModel.userPhone)
        contentValues.put(KEY_EMAIL, sqlListModel.userEmail)
        contentValues.put(KEY_PEST, sqlListModel.pest)
        contentValues.put(KEY_DATE, sqlListModel.date)
    val success=db.insert(CLIENT_TABLE,null,contentValues)
    db.close()
        return success
    }
    fun readData():List<SqlListModel> {

        val userArray: ArrayList<SqlListModel> = ArrayList<SqlListModel>()
        val selectQuery = "SELECT * FROM $CLIENT_TABLE"
        val db = this.readableDatabase
        var cursor:Cursor?= null
        try {
            cursor=db.rawQuery(selectQuery, null)

        }catch (e:SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userId:Int
        var userName:String
        var userGender:String
        var userPhone:Int
        var userEmail:String
        var pest:String
        var date:Int
        if (cursor.moveToFirst()){
         do {
             userId=cursor.getInt(cursor.getColumnIndex("Id"))
             userName= cursor.getString(cursor.getColumnIndex("UserName"))
             userGender= cursor.getString(cursor.getColumnIndex("UserGender"))
             userPhone= cursor.getInt(cursor.getColumnIndex("UserPhone"))
             userEmail= cursor.getString(cursor.getColumnIndex("UserEmail"))
             pest= cursor.getString(cursor.getColumnIndex("Pest"))
             date= cursor.getInt(cursor.getColumnIndex("Date"))

             val person = SqlListModel(userName =userName, userId =userId, userGender =userGender, userPhone =userPhone, userEmail =userEmail, pest =pest, date=date)
             userArray.add(person)
         }   while (cursor.moveToNext())


        }

        return userArray
    }

    fun updateData(sqlListModel: SqlListModel):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(KEY_ID,sqlListModel.userId)
        contentValues.put(KEY_NAME,sqlListModel.userName)
        contentValues.put(KEY_GENDER,sqlListModel.userGender)
        contentValues.put(KEY_PHONE,sqlListModel.userPhone)
        contentValues.put(KEY_EMAIL,sqlListModel.userEmail)
        contentValues.put(KEY_PEST,sqlListModel.pest)
        contentValues.put(KEY_DATE,sqlListModel.date)
        val success=db.update(CLIENT_TABLE,contentValues,"Id=" + sqlListModel.userId,null )
        db.close()
        return success
    }
    fun deleteData(sqlListModel: SqlListModel):Int{
        val db=this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(KEY_ID,sqlListModel.userId)
        //delete process
        val success= db.delete(CLIENT_TABLE,"id="+ sqlListModel.userId,null)
        db.close()
        return success
    }



}