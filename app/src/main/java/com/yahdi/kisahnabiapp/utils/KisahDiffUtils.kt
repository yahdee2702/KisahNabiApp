package com.yahdi.kisahnabiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.yahdi.kisahnabiapp.data.model.KisahResponse

class KisahDiffUtils(
    private val oldList: ArrayList<KisahResponse>,
    private val newList: ArrayList<KisahResponse>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name === newItem.name
                && oldItem.content === newItem.content
                && oldItem.age === newItem.age
                && oldItem.origin === newItem.origin
                && oldItem.birthYear === newItem.birthYear
                && oldItem.thumb === newItem.thumb
    }
}