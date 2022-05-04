package com.example.shoppinglist.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "note_list")
data class NoteItem(
    //1
    @PrimaryKey (autoGenerate = true)
    val id: Int?,

    //2
    @ColumnInfo (name = "title")
    val title: String,

    //3
    @ColumnInfo (name = "content")
    val content:String,

    //4
    @ColumnInfo (name = "time")
    val time:String,

    //5
    @ColumnInfo (name = "category")
    val category:String,
): Serializable
