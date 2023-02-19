package com.example.forstart.core.database.dao

import androidx.room.*
import com.example.forstart.core.database.relationships.StudentAndProjects
import com.example.forstart.core.database.table.Student


@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    suspend fun getAll(): List<Student>

    @Query("SELECT * FROM student")
    suspend fun getStudentAndProjectAll(): List<StudentAndProjects>

    @Query("SELECT * FROM student WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Student>

    @Query(
        "SELECT * FROM student WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: Student)

    @Delete
    suspend fun delete(user: Student)
}