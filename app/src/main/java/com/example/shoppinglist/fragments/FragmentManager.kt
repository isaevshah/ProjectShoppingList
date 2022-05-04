package com.example.shoppinglist.fragments

import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R

object FragmentManager {
    var currentFrag: BaseFragment? = null

    fun setFragment(newFrag : BaseFragment, activity: AppCompatActivity){
        val transaction = activity.supportFragmentManager.beginTransaction()// с помощю transaction  можем фрагменты удалять добавить и т.д
        transaction.replace(R.id.placeHolder, newFrag)
        transaction.commit()
        currentFrag = newFrag // уже не 0 он равен к newFrag
    }
}