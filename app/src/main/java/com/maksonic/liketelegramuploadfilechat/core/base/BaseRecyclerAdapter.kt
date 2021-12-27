package com.maksonic.liketelegramuploadfilechat.core.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

/**
 * @Author: maksonic on 25.12.2021
 */
abstract class BaseRecyclerAdapter<M : Any, WB : ViewBinding, VH : BaseViewHolder<M, WB>>(callback: DiffUtil.ItemCallback<M>) :
    ListAdapter<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings((getItem(position)))
        holder.bind()
    }

    override fun submitList(items: List<M>?) {
        super.submitList(items ?: emptyList())
        notifyDataSetChanged()
    }
}