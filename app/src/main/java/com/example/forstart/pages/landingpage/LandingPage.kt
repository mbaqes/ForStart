package com.example.forstart.pages.landingpage

import android.graphics.fonts.FontFamily
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.forstart.R
import kotlinx.coroutines.delay

@Composable
fun LandingPage(navController: NavHostController) {
    var musrataCamptxt by remember() {
        mutableStateOf("")
    }
    var musrataCamptxt2 by remember() {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true){
        musrataCamptxt= "MUSRATA "
        delay(500)
        musrataCamptxt= "$musrataCamptxt CAMP"
        delay(1000)
        musrataCamptxt2= "TOGTHER "
        delay(1000)
        navController.navigate("MainPage"){
            popUpTo("landPage"){
                inclusive=true
            }
            launchSingleTop=true
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.calculateTopPadding()
        Column(modifier = Modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text( modifier = Modifier.padding(2.dp)
                    .animateContentSize(
                        animationSpec = tween(durationMillis = 150)

                    ),
                    text = musrataCamptxt,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                     style = TextStyle(color = Color.Black.copy(alpha = 0.80f),
                         shadow = Shadow(
                         color = Color.Gray,

                         offset = Offset(x = 1f, y = 1f),
                         blurRadius = 3f
                     ), fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                     )
                )

                Text(
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = tween(durationMillis = 200)
                        )
                        .padding(horizontal = 8.dp, vertical = 10.dp),

                    text = musrataCamptxt2,
                    fontSize = 20.sp,
                    style = TextStyle(color = Color.Red.copy(alpha = 0.80f),shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(x = 1f, y = 1f),
                        blurRadius = 4f
                    )
                    ),
                    fontWeight = FontWeight.W300
                )

            }
            }



        }
    }


