package com.example.forstart.core.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("functionalty")
data class Functionalty(
    @PrimaryKey(autoGenerate = true)  val id:Int,
    val name:String,
    val isdon:Boolean=false,
)