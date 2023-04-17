package com.example.roombasic

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *    author : Chip
 *    time   : 2023/4/15
 *    desc   :
 */
class Repository(application: Application) {
    private var wordDatabase: WordDatabase = WordDatabase.getInstance(application)
    private var wordDao: WordDao = wordDatabase.wordDao

    suspend fun insertWords(vararg words: Word) {
        withContext(Dispatchers.IO) {
            //使用 * 展开运算符
            wordDao.insertWords(*words)
        }

    }

    suspend fun deleteWord(vararg words: Word) {
        withContext(Dispatchers.IO) {
            wordDao.deleteWords(*words)
        }
    }

    fun getAllWordsLive(): LiveData<List<Word?>?> {
        return wordDao.getAllWordsLive()
    }

    suspend fun deleteAllWords() {
        withContext(Dispatchers.IO) {
            wordDao.deleteAllWords()
        }
    }

    suspend fun updateWords(vararg words: Word) {
        withContext(Dispatchers.IO) {
            wordDao.updateWords(*words)
        }
    }
}
