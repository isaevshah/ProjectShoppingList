package com.example.shoppinglist.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.activities.MainApp
import com.example.shoppinglist.activities.NewNoteActivity
import com.example.shoppinglist.databinding.FragmentNoteBinding
import com.example.shoppinglist.db.MainViewModel
import com.example.shoppinglist.db.NoteAdapter
import com.example.shoppinglist.entities.NoteItem

class NoteFragment : BaseFragment() , NoteAdapter.Listener{
    private lateinit var binding: FragmentNoteBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: NoteAdapter


    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    override fun onClickNew() { // здесь будет запускаться логика для добавления новые заметки на базу данных
       editLauncher.launch(Intent(activity , NewNoteActivity::class.java)) // тут мы принимает дайта через интент
    }// через функции onClickNew мы запускаем новый активити

    override fun onCreate(savedInstanceState: Bundle?) {
        onEditResult()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView( // тут создается наш вию
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
        observer()
    }

    private fun initRecView() = with(binding){
             idRecView.layoutManager = LinearLayoutManager(activity)
            adapter =  NoteAdapter(this@NoteFragment) // init adapter)
            idRecView.adapter = adapter // refresh our resecler view
    }

    private fun observer(){ // обсервер следить за изменениями в базе данных и обновляет адаптер
        mainViewModel.allNotes.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
    }

    private fun onEditResult(){ // in this case we registered our launcher
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val editState = it.data?.getStringExtra(EDIT_STATE_KEY)
                if(editState == "update"){
                    mainViewModel.updateNote(it.data?.getSerializableExtra(NEW_NOTE_KEY) as NoteItem)
                }else{
                    mainViewModel.insertNote(it.data?.getSerializableExtra(NEW_NOTE_KEY) as NoteItem)
                }
            }
        }
    }

    override fun deleteItem(id: Int) { // вызываем функцию deleteItem
        mainViewModel.deleteNote(id)
    }

    override fun onClickItem(note: NoteItem) {
        val intent = Intent(activity, NewNoteActivity:: class.java).apply {
            putExtra(NEW_NOTE_KEY, note)
        }
        editLauncher.launch(intent)
    }
    companion object {
        const val NEW_NOTE_KEY = "new_note_key"
        const val EDIT_STATE_KEY = "edit_state_key"
        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}