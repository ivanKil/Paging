package com.kusch.pagging.data

import com.kusch.pagging.data.models.DBReddit
import com.kusch.pagging.data.models.DataPost
import com.kusch.pagging.data.models.dao.PostDao

interface CashPostDataSource {
    suspend fun getPosts(page: String): List<DBReddit>
    suspend fun savePosts(page: String, nextPage: String, posts: List<DataPost>)
}

class CashPostDataSourceImpl(val postDao: PostDao) : CashPostDataSource {
    override suspend fun getPosts(page: String): List<DBReddit> {
        return postDao.findAll(page)
    }

    override suspend fun savePosts(page: String, nextPage: String, posts: List<DataPost>) {
        postDao.addAll(posts.map { it.toDbReddit(page, nextPage) })
    }
}