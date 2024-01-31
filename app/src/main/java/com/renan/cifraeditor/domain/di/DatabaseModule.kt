package com.renan.cifraeditor.domain.di

import com.renan.cifraeditor.domain.daos.ChordDao
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.database.CifraEditorDatabase
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordChordCrossReferenceDao
import com.renan.cifraeditor.domain.daos.WordDao
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
    fun provideTomDao(appDatabase: CifraEditorDatabase): TomDao {
        return appDatabase.tomDao()
    }

    @Provides
    @Singleton
    fun provideCipherDao(appDatabase: CifraEditorDatabase): CipherDao {
        return appDatabase.cipherDao()
    }

    @Provides
    @Singleton
    fun provideWordsDao(appDatabase: CifraEditorDatabase): WordDao {
        return appDatabase.wordsDao()
    }
    @Provides
    @Singleton
    fun provideChordsDao(appDatabase: CifraEditorDatabase): ChordDao {
        return appDatabase.chordDao()
    }

    @Provides
    @Singleton
    fun provideWordChordsReference(appDatabase: CifraEditorDatabase): WordChordCrossReferenceDao {
        return appDatabase.wordChordCrossReferenceDao()
    }
}