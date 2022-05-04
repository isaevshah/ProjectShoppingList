package com.example.shoppinglist.activities

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityNewNoteBinding
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.fragments.NoteFragment
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    private var note: NoteItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSettings()
        getNote()
    }
    private  fun getNote(){
        val serializableNote = intent.getSerializableExtra(NoteFragment.NEW_NOTE_KEY)
        if(serializableNote!=null){
            note = serializableNote as NoteItem
            fillNote()
        }
    }
    private fun fillNote() = with(binding){// заполняем все поля
            edTitle.setText(note?.title)
            edDescription.setText(note?.content)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // for getting menu from xml
        menuInflater.inflate(R.menu.new_note_menu , menu )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.id_save){
            setMainResult()// create new note
        }
        else if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMainResult(){
        var editState = "new"
        val tempNote: NoteItem?
        if(note == null){
            tempNote = createNewNote()
        }else{
            editState = "update"
            tempNote = updateNote()
        }
        val i = Intent().apply { // we created Intent
            putExtra(NoteFragment.NEW_NOTE_KEY,tempNote)
            putExtra(NoteFragment.EDIT_STATE_KEY,editState)
        }
        setResult(RESULT_OK,i)
        finish()
    }

    private fun updateNote(): NoteItem? = with(binding){// update view
        return note?.copy(
            title = edTitle.text.toString(),
            content = edDescription.text.toString()
            )
    }

    private fun createNewNote():NoteItem{
        return NoteItem(
            null,
            binding.edTitle.text.toString(),
            binding.edDescription.text.toString(),
            getCurrentTime(),
            ""
        )
    }

    private fun getCurrentTime(): String{
        val formatter = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }

    private fun actionBarSettings(){
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }
}