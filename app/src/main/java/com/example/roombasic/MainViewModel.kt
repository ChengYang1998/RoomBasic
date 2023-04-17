package com.example.roombasic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *    author : Chip
 *    time   : 2023/4/17
 *    desc   :
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun insert(vararg words: Word) {
        viewModelScope.launch {


            repository.insertWords(*words)
        }

    }

    fun deleteAllWords() {
        viewModelScope.launch {
            repository.deleteAllWords()
        }

    }

    fun updateWords(vararg words: Word) {
        viewModelScope.launch {
            repository.updateWords(*words)
        }

    }

    fun deleteWord(word: Word) {
        viewModelScope.launch {
            repository.deleteWord(word)
        }

    }

    fun getAllWordsLive(): LiveData<List<Word?>?> {
        return repository.getAllWordsLive()
    }

}