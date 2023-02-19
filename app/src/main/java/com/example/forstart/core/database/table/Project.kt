package com.example.forstart.core.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date

@Entity("project")
data class Project(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    val name:String,
     val birthday: Date?,
    val studentid:Int,
)
