package com.example.forstart.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.forstart.core.database.converters.Converters
import com.example.forstart.core.database.dao.ProjectDao
import com.example.forstart.core.database.dao.StudentDao
import com.example.forstart.core.database.table.Layout
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student


@Database(entities = [Student::class, Project::class, Layout::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProjectsDB : RoomDatabase() {
    abstract fun userDao(): StudentDao
    abstract fun projectDao(): ProjectDao
    companion object{
        @Volatile
        private var database: ProjectsDB? =null
        fun initDb(context: Context?): ProjectsDB {
            if(database ==null){
                synchronized(this){
                    database = Room.databaseBuilder(
                        context!!,
                        ProjectsDB::class.java,
                        name = "campdb"
                    ).build()
                    return database!!
                }
            }
            return database!!
        }

    }
}