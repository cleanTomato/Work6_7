package com.example.work6.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.work6.data.model.Cat

@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var INSTANCE: CatDatabase? = null

        fun getDatabase(context: Context): CatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatDatabase::class.java,
                    "cat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}