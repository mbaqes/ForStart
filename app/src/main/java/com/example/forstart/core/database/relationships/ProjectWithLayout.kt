package com.example.forstart.core.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.forstart.core.database.table.Layout
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student


data class ProjectWithLayout(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "proid"
    )
    val layout: List<Layout>
)
