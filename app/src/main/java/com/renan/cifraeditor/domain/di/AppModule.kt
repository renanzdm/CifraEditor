package com.renan.cifraeditor.domain.di

import android.content.Context
import androidx.room.Room
import com.renan.cifraeditor.domain.database.CifraEditorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): CifraEditorDatabase {
        return Room.databaseBuilder(
            appContext, CifraEditorDatabase::class.java, "cifra-editor-database"
        ).build()
    }

}