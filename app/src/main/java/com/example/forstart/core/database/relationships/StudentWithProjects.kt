package com.example.forstart.core.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student

data class StudentAndProjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "uid",
        entityColumn = "studentid"
    )
    val project: List<Project>
)
