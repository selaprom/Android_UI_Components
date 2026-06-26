package kh.com.sela.android.topbartype.di.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.sela.android.topbartype.di.imp.TaskRepositoryImpl
import kh.com.sela.android.topbartype.di.imp.UserRepositoryImpl
import kh.com.sela.android.topbartype.domain.repository.TaskRepository
import kh.com.sela.android.topbartype.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}

