package kh.com.sela.android.topbartype.di.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kh.com.sela.android.topbartype.di.local.dao.TaskDao
import kh.com.sela.android.topbartype.di.local.entity.Task


@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}