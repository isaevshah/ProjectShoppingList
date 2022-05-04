package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.entities.LibraryItem
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.entities.ShoppingListItem
import com.example.shoppinglist.entities.ShoppingListNames

@Database (entities = [LibraryItem::class, NoteItem::class,
    ShoppingListItem::class,ShoppingListNames::class], version = 1)
 abstract class MainDataBase: RoomDatabase() { // с помощью этого класса мы получаем интерфейс Dao

    abstract fun getDao(): Dao // эта функция возрврашает интерфейс Dao

     companion object{
         @Volatile
         private var INSTANCE: MainDataBase? = null
         fun getDataBase(context: Context): MainDataBase{
             return INSTANCE ?: synchronized(this){ // если база данных равен 0 тогда завпускается код который в правым написано
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     MainDataBase:: class.java,
                     "shopping_list.db" // create file for DB
                 ).build()
                 instance
             }
         }
     }
}