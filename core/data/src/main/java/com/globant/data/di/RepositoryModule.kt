package com.globant.data.di

import com.globant.data.repository.HarryPotterRepository
import com.globant.data.repository.HarryPotterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindHarryPotterRepository(
        repository: HarryPotterRepositoryImpl
    ): HarryPotterRepository
}