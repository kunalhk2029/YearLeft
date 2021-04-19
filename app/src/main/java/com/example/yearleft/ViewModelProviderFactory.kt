package com.example.yearleft

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.newsstories.ArticleDatabase

class YearLeftViewModelProviderFactory(  val repo : Repository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return YearLeftViewModel(repo) as T
    }
}