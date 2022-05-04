package com.example.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "shopping_list_names") // если есть у нас список тогда нам понадобиться это!
data class ShoppingListNames(
    // first column
    @PrimaryKey (autoGenerate = true) // эта строчка для того что бы генерировать автоматом каждый элемент
    val id:Int?,

    // second column
    @ColumnInfo (name = "name") // тут будет хранятся данные типа стринг например -Вечерная покупка-
    val name: String,

    // third column
    @ColumnInfo (name = "time") // сохраняем время
    val time: String,

    // fourth column
    @ColumnInfo (name = "allItemCount") // сколько осталось товаров
    val allItemCount:Int,

    // fifth column
    @ColumnInfo (name = "checkedItemsCounter") // сколько мы продали
    val checkedItemsCounter:Int,

    @ColumnInfo (name = "itemsIds") // айдишки всех элементов
    val itemsIds: Int,
) : Serializable
