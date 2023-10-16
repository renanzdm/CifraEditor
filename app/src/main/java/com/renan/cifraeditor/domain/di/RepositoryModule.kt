package com.renan.cifraeditor.domain.di

import com.renan.cifraeditor.data.repository.TomRepositoryImpl
import com.renan.cifraeditor.domain.repository.TomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

@Binds
abstract fun provideRomRepository(tomRepository: TomRepositoryImpl):TomRepository

}