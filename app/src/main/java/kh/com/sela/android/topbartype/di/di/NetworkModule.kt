package kh.com.sela.android.topbartype.di.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.sela.android.topbartype.network.ApiService
import kh.com.sela.android.topbartype.network.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        return RetrofitClient.apiService

    }
}