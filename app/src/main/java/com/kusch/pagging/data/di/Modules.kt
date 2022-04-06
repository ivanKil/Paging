package com.kusch.pagging.data.di

import android.app.Application
import androidx.room.Room
import com.kusch.pagging.data.AppDatabase
import com.kusch.pagging.data.CashPostDataSource
import com.kusch.pagging.data.CashPostDataSourceImpl
import com.kusch.pagging.data.MyApi
import com.kusch.pagging.data.models.dao.PostDao
import com.kusch.pagging.ui.PostDataSource
import com.kusch.pagging.ui.PostViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "posts.database")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    fun provideDao(database: AppDatabase): PostDao {
        return database.postDao
    }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

    single { CashPostDataSourceImpl(get()) as CashPostDataSource }
    single { MyApi() }
    single { PostDataSource(get(), get()) }
    viewModel { PostViewModel(get()) }
}
