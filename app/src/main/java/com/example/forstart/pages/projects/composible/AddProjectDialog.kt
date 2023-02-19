package com.example.forstart.pages.projects.composible

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.forstart.core.database.table.Project
import com.example.forstart.core.database.table.Student
import com.example.forstart.pages.projects.ProjectViewModel
import java.util.Calendar

@Composable
fun AddProjectDialog(

    viewModelProject: ProjectViewModel,
    txtprojectName: String,
    mSelectedText: Student,
    show: (Boolean) -> Unit,
    projectNameChange: (String) -> Unit,
    ) {
    AlertDialog(onDismissRequest = {
        show(false)
    }, text = {
        Column {
            OutlinedTextField(placeholder = {
                Text(text = "Enter Name")
            }, value = txtprojectName, onValueChange = { newName ->
                projectNameChange(newName)
            })


        }
    }, confirmButton = {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                viewModelProject.addProject(
                    Project(
                        name = txtprojectName,
                        studentid = mSelectedText.uid!!,
                        birthday = Calendar.getInstance().time
                    )
                )
                Log.d("22", "----*****------>>>>>" + mSelectedText.uid.toString())
                show(false)
            }) {
                Text(text = "Add")
            }
            Button(onClick = { show(false) }) {
                Text(text = "Back")
            }
        }
    })

}