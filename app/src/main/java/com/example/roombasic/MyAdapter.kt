package com.example.roombasic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roombasic.databinding.ItemCardBinding


/**
 *    author : Chip
 *    time   : 2023/4/17
 *    desc   :
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var allWords: List<Word>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = allWords[position]
        holder.bind(item, position)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "click", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return allWords.size
    }

    class MyViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // 绑定数据项
        @SuppressLint("SetTextI18n")
        fun bind(item: Word, position: Int) {
            binding.textViewNumber.text = (position + 1).toString()
            binding.textViewEnglish.text = item.word
            binding.textViewChinese.text = item.chineseMeaning
        }
    }


}