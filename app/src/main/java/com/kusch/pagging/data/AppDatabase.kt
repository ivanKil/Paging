package com.kusch.pagging.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kusch.pagging.data.models.DBReddit
import com.kusch.pagging.data.models.dao.PostDao

@Database(entities = [DBReddit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val postDao: PostDao
}