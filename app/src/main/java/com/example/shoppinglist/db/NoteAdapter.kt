package com.example.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.NoteListItemBinding
import com.example.shoppinglist.entities.NoteItem

class NoteAdapter(private val listener: Listener): ListAdapter<NoteItem, NoteAdapter.ItemHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent) // тут создается viewHolder
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener) // заполнается
    }

    class ItemHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = NoteListItemBinding.bind(view) // преврашаем xml на binding что бы достучаться на их элементов
        fun setData(note: NoteItem, listener: Listener) = with(binding){ // тут мы передаем NoteItem он по очереди будет брать данные
                idTitle.text = note.title
                idDescription.text = note.content
                idTime.text = note.time
                itemView.setOnClickListener{
                    listener.onClickItem(note)
                }
                inDelete.setOnClickListener{
                    listener.deleteItem(note.id!!)
                }
        }
        companion object { // тут мы иницилизируем ItemHolder
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(
                    LayoutInflater.from(parent.context).
                            inflate(R.layout.note_list_item,parent, false)
                )
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<NoteItem>(){
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id == newItem.id // тут проверяет айтмы (один и тот же айтм или нет)
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem==newItem // тут сравнивает весь айтмы
        }
    }
    interface Listener{
        fun deleteItem(id: Int)
        fun onClickItem(note: NoteItem)// при нажатии передадим всю заметку
    }
}