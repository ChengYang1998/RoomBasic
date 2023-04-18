package com.example.roombasic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roombasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 在 Activity 或 Fragment 中声明一个 ViewBinding 变量
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private var myAdapter: MyAdapter = MyAdapter()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 使用 ViewBinding 类绑定视图
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //观察LiveData
        viewModel.getAllWordsLive().observe(this) {
            if (it != null) {
                myAdapter.allWords = it.filterNotNull()
                binding.recyclerView.adapter = myAdapter
            }
        }


        binding.btnInsert.setOnClickListener {

            val english = arrayOf(
                "Hello",
                "World",
                "Android",
                "Google",
                "Studio",
                "Project",
                "Database",
                "Recycler",
                "View",
                "String",
                "Value",
                "Integer"
            )
            val chinese =
                arrayOf("你好", "世界", "安卓", "谷歌", "工作室", "工程", "数据库", "回收站", "视图", "字符串", "值", "整型")

            for (i in english.indices) {
                val en = english[i]
                val ch = chinese[i]
                viewModel.insert(Word(word = en, chineseMeaning = ch))

            }
        }

        binding.btnClear.setOnClickListener {
            viewModel.deleteAllWords()
        }

        binding.btnUpdate.setOnClickListener {
            val word = Word(id = 185, word = "Hi", chineseMeaning = "你好啊")
            viewModel.updateWords(word)
        }
        binding.btnDelete.setOnClickListener {
            val word = Word(id = 182)
            viewModel.deleteWord(word)
        }
    }
}