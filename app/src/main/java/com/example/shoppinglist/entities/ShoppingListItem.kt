package com.example.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "shop_list_item")
data class ShoppingListItem(
    //1
    @PrimaryKey (autoGenerate = true)
    val id:Int?,

    //2
    @ColumnInfo(name = "name")
    val name:String,

    //3
    @ColumnInfo (name = "itemInfo")
    val itemInfo:String?,

    //4
    @ColumnInfo (name = "itemChecked")
    val itemChecked: Int = 0,

    //5
    @ColumnInfo (name = "listId")
    val listId: Int,

    //6
    @ColumnInfo (name = "itemType")
    val itemType: String = "item"
)
