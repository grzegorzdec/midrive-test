package com.midrive.learnerapp.activity.lesson

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.midrive.learnerapp.databinding.LessonListContentItemBinding
import com.midrive.learnerapp.databinding.LessonListHeaderItemBinding

const val HEADER_VIEW_TYPE = 0
const val CONTENT_VIEW_TYPE = 1

class LessonListAdapter(private val viewModels: ObservableList<LessonListAdapterItem>)
    : RecyclerView.Adapter<LessonListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonListAdapter.ViewHolder =
            when (viewType) {
                CONTENT_VIEW_TYPE -> ViewHolder(LessonListContentItemBinding.inflate(LayoutInflater.from(parent.context)))
                else -> ViewHolder(LessonListHeaderItemBinding.inflate(LayoutInflater.from(parent.context)))
            }

    override fun getItemCount(): Int = viewModels.size

    override fun onBindViewHolder(holder: LessonListAdapter.ViewHolder, position: Int) {
        viewModels[position].onBindExtras(holder.viewBinding, position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewModels[position].bindableItem) {
            is LessonContentViewModel -> CONTENT_VIEW_TYPE
            else -> HEADER_VIEW_TYPE
        }
    }

    inner class ViewHolder(val viewBinding: android.databinding.ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root)
}