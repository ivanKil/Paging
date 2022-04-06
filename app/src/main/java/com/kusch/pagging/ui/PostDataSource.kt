package com.kusch.pagging.ui

import androidx.paging.PagingSource
import com.kusch.pagging.data.CashPostDataSource
import com.kusch.pagging.data.MyApi
import com.kusch.pagging.data.models.DataPost
import com.kusch.pagging.data.models.RedditResponse

class PostDataSource(private val api: MyApi, val cash: CashPostDataSource) :
    PagingSource<String, DataPost>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, DataPost> {
        return try {
            val dbReddits = cash.getPosts(params.key ?: "")
            if (dbReddits.size > 0)
                LoadResult.Page(dbReddits.map { it.toDataPost() }, null, dbReddits[0].next_page)
            else {
                val response: RedditResponse = api.getPosts(params.key ?: "")
                cash.savePosts(params.key ?: "", response.data.after, response.data.children)
                LoadResult.Page(response.data.children, null, response.data.after)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}