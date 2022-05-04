package com.example.shoppinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.entities.NoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes(): Flow<List<NoteItem>> // эта для того что бы обновить наш список
    @Query("DELETE FROM note_list WHERE id IS :id")// тут sqlite понимает что надо удалить по id
    suspend fun deleteNote(id: Int )// s pomoshu naydem i udalim
    @Insert
    suspend fun insertNote(note: NoteItem)
    @Update
    suspend fun updateNote(note: NoteItem)
}