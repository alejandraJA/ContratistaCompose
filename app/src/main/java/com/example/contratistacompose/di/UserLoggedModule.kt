package com.example.contratistacompose.di

import com.example.contratistacompose.data.repository.local.LocalLoggedRepositoryImp
import com.example.contratistacompose.domain.UserLoggedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserLoggedModule {
    @Singleton
    @Provides
    fun provideUserService(repository: LocalLoggedRepositoryImp): UserLoggedRepository = repository
}