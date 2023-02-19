package com.example.forstart.pages.projects

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.forstart.core.database.table.Student
import com.example.forstart.pages.projects.composible.AddProjectDialog
import java.time.LocalDate

@Composable
fun ProjactPage(
    navController: NavHostController,
    viewModelProject:ProjectViewModel =hiltViewModel()) {
//    val viewModelProject = viewModel<ProjectViewModel>()
    var state = viewModelProject.state.collectAsState().value
    Log.d("22", "----->> " + state.student.size.toString())
    var txtprojectName by remember {
        mutableStateOf("")
    }
    val defualt = Student(
        firstName = "Select Student",
        lastName = ""
    )
    var mSelectedText by remember {
        mutableStateOf(
            defualt
        )
    }

    var mExpanded by remember { mutableStateOf(false) }
    var txtlastName by remember {
        mutableStateOf("")
    }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var showAdd by remember {
        mutableStateOf(false)
    }
    if (showAdd) {
        AddProjectDialog(
            viewModelProject = viewModelProject,
            txtprojectName = txtprojectName,
            mSelectedText = mSelectedText, show = {
                showAdd = it
            }, projectNameChange = {
                txtprojectName = it
            })
    }
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Student Projects : ")


                    Text(text = state.projects.size.toString())

            }
        }
    ) {
        it.calculateTopPadding()
        Column {
            Box(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,

                    modifier= Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.Gray
                        )
                        .clickable {
                            if (mSelectedText.uid == null) {
                                mExpanded = !mExpanded
                            } else {
                                mSelectedText = defualt
                                viewModelProject.getProjects()
                            }
                        }) {
                    Spacer(modifier = Modifier.width(10.dp))
                    if (mSelectedText.uid == null) Icon(
                        Icons.Default.Menu,
                        contentDescription = "",
                        ) else
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "", tint = Color.Red
                          )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = mSelectedText.firstName.toString(), fontSize = 16.sp)


                }
//                Text(
//                    readOnly = true,
//
//                    leadingIcon = {
//
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    value = mSelectedText.firstName.toString(),
//                    onValueChange = {})
                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                ) {
                    state.student.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                mExpanded = false
                                mSelectedText = item
                                viewModelProject.getProjectByStudent(item.uid!!)

                            },
                        ) {
                            Text(text = item.firstName.toString())
                        }
                    }
                }
            }

            /////
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {

                if (mSelectedText.uid != null) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .clickable {
                                    showAdd = true
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Add Project to ", fontSize = 14.sp)
                            Text(
                                text = "${mSelectedText.firstName}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                }

                items(state.projects) { itemProject ->

                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = 5.dp
                    ) {
                        Column {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TableCell(text = "Project Name", weight = 4f, FontWeight.Bold)

                                TableCell(text = itemProject.project.name, weight = 7f)
                            }

                            Divider(thickness = 1.dp)
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TableCell(text = "Create In", weight = 4f, FontWeight.Bold)

                                TableCell(
                                    text = itemProject.project.birthday.toString(),
                                    weight = 7f
                                )
                            }

                        }
                    }

                }
            }
        }
///////////////////


    }

}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(8.dp),
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
    )
}
