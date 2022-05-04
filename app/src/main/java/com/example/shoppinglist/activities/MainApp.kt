package com.example.shoppinglist.activities

import android.app.Application
import com.example.shoppinglist.db.MainDataBase

class MainApp: Application() { // эта инстанция на уровне всех активити!
    val database by lazy { MainDataBase.getDataBase(this) }
}