package com.kusch.pagging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kusch.pagging.data.PAGE_SIZE

class PostViewModel(private val postDataSource: PostDataSource) : ViewModel() {
    val posts = Pager(PagingConfig(PAGE_SIZE, 1), null, null)
    { postDataSource }.flow.cachedIn(viewModelScope)
}
