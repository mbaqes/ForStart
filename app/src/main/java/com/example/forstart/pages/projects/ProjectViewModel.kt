package com.example.forstart.pages.projects

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forstart.core.database.ProjectsDB
import com.example.forstart.core.database.relationships.ProjectWithLayout
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProjecUItState(
    var student: List<Student> = emptyList(),
    var projects: List<ProjectWithLayout> = emptyList()
)
@HiltViewModel
class ProjectViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val database = ProjectsDB.initDb(null)
    var state = MutableStateFlow<ProjecUItState>(ProjecUItState())

    init {
        savedStateHandle.get<String>("id")?.let { coinId->
            Log.d("ss","------****---->"+coinId.toString())
        }
        getStudents()
    }

    fun getStudents() {
        viewModelScope.launch {
            val tempList = database.userDao().getAll().toMutableList()
            state.value = state.value.copy(student = tempList)
            getProjects()
        }

    }
    fun getProjectByStudent(studentID:Int) {
        viewModelScope.launch {
            val tempList = database.projectDao().getByStudentID(studentID).toMutableList()
            state.value = state.value.copy(projects = tempList)

        }

    }

    fun getProjects() {
        viewModelScope.launch {
            var tempList = database.projectDao().getProjectAndLayoutAll().toMutableList()
            state.value =   state.value.copy(projects = tempList)
        }

    }

    fun remove(student: Project) {
        viewModelScope.launch {
            database.projectDao().delete(student)
            getProjects()
        }
    }

    fun addProject(student: Project) {
        viewModelScope.launch {
            database.projectDao().insertAll(student)
            getProjects()
        }
    }

}