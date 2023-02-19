package com.example.forstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forstart.pages.cartpage.StudentViewModel
import com.example.forstart.core.database.ProjectsDB
import com.example.forstart.pages.landingpage.LandingPage
import com.example.forstart.pages.mainpage.MainPage
import com.example.forstart.pages.projects.ProjactPage
import com.example.forstart.ui.theme.ForStartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProjectsDB.initDb(this)
        setContent {
            //
            ForStartTheme {
                val navController = rememberNavController()
                val studentViewModel = viewModel<StudentViewModel>()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = "landPage" ){

                        composable("MainPage") {
                            MainPage(navController, studentViewModel = studentViewModel)
                        }
                        composable("landPage") {
                            LandingPage(navController)
                        }
                        composable("ProjectPage"+ "/{id}"){
                            ProjactPage(navController = navController)
                        }

                    }
                    
                }
            }
        }
    }
}

