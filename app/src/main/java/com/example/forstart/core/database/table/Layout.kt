package com.example.forstart.core.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("layout")
data class Layout(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val proid:Int,
    val isdon:Boolean=false,
)