package com.example.tugasmodul

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodul.databinding.ItemHomeworkBinding

class HomeworkAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    var listHomework = ArrayList<Homework>()
        set(value) {
            this.listHomework.apply {
                clear()
                addAll(value)
            }
        }

    interface OnItemClickCallback {
        fun onItemClicked(selectedHomework: Homework?, position: Int?)
    }

    /**
     * Menambahkan item baru ke daftar
     */
    fun addItem(homework: Homework) {
        listHomework.add(homework)
        notifyItemInserted(listHomework.size - 1)
    }

    /**
     * Memperbarui item pada posisi tertentu
     */
    fun updateItem(position: Int, homework: Homework) {
        listHomework[position] = homework
        notifyItemChanged(position)
    }

    /**
     * Menghapus item pada posisi tertentu
     */
    fun removeItem(position: Int) {
        listHomework.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listHomework.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val binding = ItemHomeworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeworkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(listHomework[position])
    }

    override fun getItemCount(): Int = listHomework.size

    /**
     * ViewHolder untuk menangani tampilan item
     */
    inner class HomeworkViewHolder(private val binding: ItemHomeworkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homework: Homework) {
            binding.tvItemTitle.text = homework.title
            binding.tvItemDate.text = homework.date
            binding.tvItemDescription.text = homework.description

            binding.cvItemHomework.setOnClickListener {
                onItemClickCallback.onItemClicked(homework, adapterPosition)
            }
        }
    }
}
