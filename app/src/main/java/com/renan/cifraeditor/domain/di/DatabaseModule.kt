package com.renan.cifraeditor.domain.di

import com.renan.cifraeditor.domain.database.CifraEditorDatabase
import com.renan.cifraeditor.domain.daos.TomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: CifraEditorDatabase): TomDao {
        return appDatabase.tomDao()
    }
}