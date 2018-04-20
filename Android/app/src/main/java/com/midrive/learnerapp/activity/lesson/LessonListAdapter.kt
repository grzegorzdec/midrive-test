package com.midrive.learnerapp.activity.lesson

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.midrive.learnerapp.databinding.LessonListItemBinding

class LessonListAdapter(private val viewModels: ObservableList<LessonViewModel>) : RecyclerView
.Adapter<LessonListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LessonListItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int =
            viewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.apply {
            viewModel = viewModels[position]
            executePendingBindings()
        }
    }

    inner class ViewHolder(val viewBinding: LessonListItemBinding) : RecyclerView.ViewHolder(viewBinding.root)
}