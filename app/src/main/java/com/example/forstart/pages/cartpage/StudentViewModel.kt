package com.example.forstart.pages.cartpage

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forstart.pages.cartpage.data.repository.CartRepository
import com.example.forstart.core.database.ProjectsDB
import com.example.forstart.core.database.relationships.StudentAndProjects
import com.example.forstart.core.database.table.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StudentViewModel(savestate:SavedStateHandle) : ViewModel() {
    private val cartRepository = CartRepository()
    val state = MutableStateFlow(mutableStateListOf<StudentAndProjects>())
    var tempList = mutableListOf<StudentAndProjects>()
    val database = ProjectsDB.initDb(null)

    init {


        getStudents()
    }

    fun getStudents() {
        viewModelScope.launch {
            tempList = database.userDao().getStudentAndProjectAll().toMutableList()
            reloadList()
        }
    }

    fun remove(student: Student) {
        viewModelScope.launch {
            database.userDao().delete(student)
            getStudents()
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            database.userDao().insertAll(student)
            getStudents()
        }
    }

    fun reloadList() {
        state.value = tempList.toMutableStateList()
    }
}