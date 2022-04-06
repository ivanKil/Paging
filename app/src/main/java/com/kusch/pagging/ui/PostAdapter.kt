package com.kusch.pagging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kusch.pagging.data.models.DataPost
import com.kusch.pagging.databinding.ItemPostBinding


class PostAdapter :
    PagingDataAdapter<DataPost, PostAdapter.PostViewHolder>(PostComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassenger(item: DataPost) = with(binding) {
            title.text = item.data.title
            commentsCount.text = item.data.num_comments.toString()
            starsCount.text = item.data.ups.toString()
        }
    }

    object PostComparator : DiffUtil.ItemCallback<DataPost>() {
        override fun areItemsTheSame(oldItem: DataPost, newItem: DataPost): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(oldItem: DataPost, newItem: DataPost): Boolean {
            return oldItem == newItem
        }
    }
}