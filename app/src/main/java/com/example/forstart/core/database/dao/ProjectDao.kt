package com.example.forstart.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.forstart.core.database.relationships.ProjectWithLayout
import com.example.forstart.core.database.relationships.StudentAndProjects
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student


@Dao
interface ProjectDao {
    @Query("SELECT * FROM project WHERE studentid = :studentid")
    suspend  fun getByStudentID( studentid:Int): List<ProjectWithLayout>
    @Query("SELECT * FROM project")
   suspend fun getProjectAndLayoutAll(): List<ProjectWithLayout>
    @Insert
    suspend fun insertAll(vararg project: Project)

    @Delete
    suspend  fun delete(project: Project)
}