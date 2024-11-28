package com.example.modul7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul7.databinding.ItemHomeworkBinding

class HomeworkAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    var listHomework = ArrayList<Homework>()
        set(value) {
            if (value.isNotEmpty()) {
                this.listHomework.clear()
            }
            this.listHomework.addAll(value)
            notifyDataSetChanged()
        }

    interface OnItemClickCallback {
        fun onItemClicked(selectedHomework: Homework?, position: Int?)
    }

    fun addItem(homework: Homework) {
        this.listHomework.add(homework)
        notifyItemInserted(this.listHomework.size - 1)
    }

    fun updateItem(position: Int, homework: Homework) {
        this.listHomework[position] = homework
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        this.listHomework.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listHomework.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
        return HomeworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(listHomework[position])
    }

    override fun getItemCount(): Int = this.listHomework.size

    inner class HomeworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHomeworkBinding.bind(itemView)

        fun bind(homework: Homework) {
            binding.tvItemTitle.text = homework.title
            binding.tvItemDate.text = homework.date
            binding.tvItemDesription.text = homework.description
            binding.cvItemHomework.setOnClickListener {
                onItemClickCallback.onItemClicked(homework, adapterPosition)
            }
        }
    }
}
