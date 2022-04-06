package com.kusch.pagging.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class RedditResponse(val data: DataResponse)

data class DataResponse(val after: String, val children: List<DataPost>)

data class DataPost(val data: DataPostInfo) {
    fun toDbReddit(page: String, nextPage: String) =
        DBReddit(null, page, data.title, data.ups, data.num_comments, nextPage)
}

data class DataPostInfo(val title: String, val ups: Int, val num_comments: Int)

@Parcelize
@Entity(tableName = "posts")
data class DBReddit(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val page: String,
    val title: String,
    val ups: Int,
    val num_comments: Int,
    val next_page: String,
) : Parcelable {
    fun toDataPost() = DataPost(DataPostInfo(title, ups, num_comments))
}