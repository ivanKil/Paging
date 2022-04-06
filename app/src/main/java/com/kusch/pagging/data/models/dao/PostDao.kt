package com.kusch.pagging.data.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kusch.pagging.data.models.DBReddit

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(dbPosts: List<DBReddit>)

    @Query("SELECT * FROM posts where page=:page")
    fun findAll(page: String): List<DBReddit>
}