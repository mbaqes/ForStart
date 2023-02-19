package com.example.forstart.pages.cartpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.forstart.core.database.table.Student
import kotlin.random.Random

@Composable
fun StudentPage(studentViewModel: StudentViewModel, navController: NavHostController) {
    val state = studentViewModel.state.collectAsState().value
    var txtFirstName by remember {
        mutableStateOf("")
    }
    var txtlastName by remember {
        mutableStateOf("")
    }
    var showAdd by remember {
        mutableStateOf(false)
    }
    if (showAdd) {
        AlertDialog(onDismissRequest = {
            showAdd = false
        }, text = {
            Column {
                OutlinedTextField(placeholder = {
                    Text(text = "Enter Name")
                }, value = txtFirstName, onValueChange = { newName ->
                    txtFirstName = newName
                })
                OutlinedTextField(placeholder = {
                    Text(text = "Enter Last Name")
                }, value = txtlastName, onValueChange = { newLastName ->
                    txtlastName = newLastName
                })

            }
        }, confirmButton = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    studentViewModel.addStudent(
                        Student(
                            firstName = txtFirstName,
                            lastName = txtlastName,
                        )
                    )
                    showAdd = false
                }) {
                    Text(text = "Add")
                }
                Button(onClick = { showAdd = false }) {
                    Text(text = "Back")
                }
            }
        })
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { showAdd = true }) {

            Icon(Icons.Rounded.Add, contentDescription = "")

        }
    },
        drawerContent = {

            Text(text = "hfhf")
        },
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Student System")
                    Row {
                        Icon(Icons.Rounded.Person, contentDescription = "")
                        Text(text = state.size.toString())
                    }

                }
            }
        }) {
        it.calculateTopPadding()
        Column {

            LazyColumn {
                items(state) { student ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("ProjectPage" + "/${student.student.firstName}"){

                                }
                            }
                            .padding(5.dp), elevation = 5.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Person,
                                contentDescription = "",
                                modifier = Modifier.size(80.dp),
                            )
                            Column {
                                Row {
                                    Text(text = "Name : ")
                                    Text(text = student.student.firstName.toString())
                                }
                                Row {
                                    Text(text = "Last Name : ")
                                    Text(text = student.student.lastName.toString())
                                }
                                Row {
                                    Text(text = "Projects : ")
                                    Text(text = student.project.size.toString())
                                }

                            }
                            Button(modifier = Modifier.fillMaxHeight(),
                                onClick = { studentViewModel.remove(student.student) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }


                    }
                }

            }
        }
    }
}

sealed class Route {
    class HomePage(var title: String, var routeName: String, var icon: ImageVector)
}