package com.hellcorp.drugstore.druglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.hellcorp.drugstore.domain.models.Drug

class DiffCallback(
    private val oldList: List<Drug>,
    private val newList: List<Drug>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList.size != newList.size && oldItemPosition == oldList.lastIndex) {
            return false
        }
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}