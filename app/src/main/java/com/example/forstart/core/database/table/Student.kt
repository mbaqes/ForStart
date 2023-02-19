package com.example.forstart.core.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("student")
data class Student(
    @PrimaryKey(autoGenerate = true) val uid: Int?=null,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?

)