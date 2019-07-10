package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class CupoDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db : SQLiteDatabase){
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertCupo(cupo: CupoModel): Boolean{
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBContract.CupoEntry.COLUMN_USER_ID, cupo.userid)
        values.put(DBContract.CupoEntry.CUPO, cupo.cupo)
        val newRowId = db.insert(DBContract.CupoEntry.TABLE_NAME, null, values)
        return true
    }

    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"
        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.CupoEntry.TABLE_NAME + "( " +
                DBContract.CupoEntry.COLUMN_USER_ID+" TEXT PRIMARY KEY,"+
                DBContract.CupoEntry.CUPO + " TEXT )"
        private val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + DBContract.CupoEntry.TABLE_NAME
    }
    fun getCupo():String{
        val cupo = ArrayList<CupoModel>()
        val db = writableDatabase
        var cursor: Cursor?=null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.CupoEntry.TABLE_NAME, null)
        }
        catch (e: SQLiteException){

        }
        var cupoS: String = "0"
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                cupoS = cursor.getString(cursor.getColumnIndex(DBContract.CupoEntry.CUPO))
                println("cupo de get cupo "+ cupoS)
                cursor.moveToNext()
            }
        }
        return cupoS
    }
    fun updateCupo(cupo: String){
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            //INSERT OR IGNORE INTO my_table (name, age) VALUES ('Karen', 34)
            //UPDATE my_table SET age = 34 WHERE name='Karen'
            // UPDATE COMPANY SET ADDRESS = 'Texas' WHERE ID = 6;
            //val query "INSERT OR IGNORE INTO" + DBContract.CupoEntry.TABLE_NAME + "( "+DBContract.CupoEntry.CUPO+ " ) VALUES ( ' " + cupo + " ')"
            var queryUpdate = "UPDATE " + DBContract.CupoEntry.TABLE_NAME + " SET "+ DBContract.CupoEntry.CUPO + " = '" + cupo +"'"
            db.execSQL(queryUpdate)
        }
        catch(e : SQLiteException){
            println("Excepcio")
            //db.execSQL(SQL_CREATE_ENTRIES)
        }
    }
}
