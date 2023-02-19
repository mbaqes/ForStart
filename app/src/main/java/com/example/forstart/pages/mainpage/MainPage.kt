package com.example.forstart.pages.mainpage

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forstart.pages.cartpage.StudentPage
import com.example.forstart.pages.cartpage.StudentViewModel
import com.example.forstart.pages.landingpage.LandingPage
import com.example.forstart.pages.projects.ProjactPage

@Composable
fun MainPage(navController: NavHostController, navControllermain: NavHostController= rememberNavController(), studentViewModel: StudentViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavigation(){
                BottomNavigationItem(selected = false, 
                    onClick = { navControllermain.navigate("homePage"){
                        popUpTo("homePage")
                        launchSingleTop=true
                    } }, label = { Text(text = "Home")},
                    icon = {}
                )
                BottomNavigationItem(selected = false,
                    onClick = { navControllermain.navigate("ProjectPage"){
                        popUpTo("homePage")
                        launchSingleTop=true
                    } }, label = { Text(text = "Project")},
                    icon = {}
                )
            }
        }
    ) {
        it.calculateTopPadding()
        it.calculateBottomPadding()
        NavHost(
            navController = navControllermain,
            startDestination = "homePage",
            modifier = Modifier.padding(bottom = 50.dp)) {
            composable("homePage") {
                StudentPage(studentViewModel, navController)
            }
            composable("ProjectPage") {
                ProjactPage(navController)
            }
        }
    }


}