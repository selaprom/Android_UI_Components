package kh.com.sela.android.topbartype.di.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kh.com.sela.android.topbartype.di.local.AppDatabase
import kh.com.sela.android.topbartype.di.local.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "task_database"
        ).fallbackToDestructiveMigration(dropAllTables = false)
            .build()

    }

    @Provides
    @Singleton
    fun provideTaskDao(
        appDatabase: AppDatabase
    ): TaskDao {
        return appDatabase.taskDao()
    }
}