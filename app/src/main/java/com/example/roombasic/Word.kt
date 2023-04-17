package com.example.roombasic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *    author : Chip
 *    time   : 2023/4/14
 *    desc   :
 */
@Entity
data class Word(


    @PrimaryKey(true)
    val id: Int = 0,

    @ColumnInfo("english_word")
    val word: String = "",

    @ColumnInfo("chinese_meaning")
    val chineseMeaning: String = "",


    )









