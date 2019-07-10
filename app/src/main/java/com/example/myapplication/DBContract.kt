package com.example.myapplication

import android.provider.BaseColumns

object DBContract{
    class CupoEntry: BaseColumns {
        companion object {
            val TABLE_NAME = "cupo"
            val COLUMN_USER_ID = "userid"
            val CUPO = "cupo"
        }
    }
}