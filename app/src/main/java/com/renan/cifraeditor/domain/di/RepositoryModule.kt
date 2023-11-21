package com.renan.cifraeditor.domain.di

import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

@Binds
abstract fun provideRomRepository(tomRepository: LocalRepositoryImpl):LocalRepository

}