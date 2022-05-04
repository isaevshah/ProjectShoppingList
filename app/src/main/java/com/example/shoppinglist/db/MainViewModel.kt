package com.example.shoppinglist.db

import androidx.lifecycle.*
import com.example.shoppinglist.entities.NoteItem
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(database: MainDataBase) : ViewModel() { // это наш посредник мы будем тут записывать и считывать данные (MVVM)
   private val dao = database.getDao() // мы тут получили Dao interface
    val allNotes: LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData() // тут мы можем прослуживать есть ли изменения в наших заметках
    // еще мы можем написать такие функции например как поиск по базу данных , списков с покупками
    fun insertNote(note: NoteItem) = viewModelScope.launch{ // корутина это для того что бы не захламлять весь пользовательский интерфейс
        dao.insertNote(note) // business logical
    }
    fun updateNote(note: NoteItem) = viewModelScope.launch{ // корутина это для того что бы не захламлять весь пользовательский интерфейс
        dao.updateNote(note) // business logical
    }
    fun deleteNote(id: Int) = viewModelScope.launch{ // корутина это для того что бы не захламлять весь пользовательский интерфейс
        dao.deleteNote(id) // business logical
    }
    class MainViewModelFactory(val database: MainDataBase) : ViewModelProvider.Factory {// мы запускаем этот класс для того что бы инициализировать class MainViewModel
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}
