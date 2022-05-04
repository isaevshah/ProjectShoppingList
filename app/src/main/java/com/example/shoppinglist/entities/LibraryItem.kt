package com.example.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "library")
data class LibraryItem(
    //1
    @PrimaryKey (autoGenerate = true)
    val id:Int?,
    //2
    @ColumnInfo (name = "name")
    val name:String
)
