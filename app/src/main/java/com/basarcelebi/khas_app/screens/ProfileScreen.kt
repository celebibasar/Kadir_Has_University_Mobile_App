package com.basarcelebi.khas_app.screens

import androidx.compose.animation.expandIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.basarcelebi.khas_app.R
import com.basarcelebi.khas_app.data.ProfileData
import com.basarcelebi.khas_app.data.SemesterGradeData
import com.basarcelebi.khas_app.data.SubjectData
import com.basarcelebi.khas_app.ui.theme.googledisplay
import com.basarcelebi.khas_app.ui.theme.googlesans
import com.basarcelebi.khas_app.ui.theme.googlesansbold

@Composable
fun ProfileScreen(navController: NavController = rememberNavController()) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = Color.White,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(15.dp))
        Card(modifier = Modifier.fillMaxWidth()) {


                Row(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(5.dp)

                    .align(Alignment.CenterHorizontally)
                ) {
                    val rainbowColorsBrush = remember {
                        Brush.sweepGradient(
                            listOf(
                                Color(0xFF9575CD),
                                Color(0xFFBA68C8),
                                Color(0xFFE57373),
                                Color(0xFFFFB74D),
                                Color(0xFFFFF176),
                                Color(0xFFAED581),
                                Color(0xFF4DD0E1),
                                Color(0xFF9575CD)
                            )
                        )
                    }
                    val borderWidth = 4.dp
                    Image(painter = painterResource(id = R.drawable.basar_celebi), contentDescription = null, modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(75.dp)
                        .border(
                            BorderStroke(borderWidth, rainbowColorsBrush),
                            CircleShape
                        )
                        .clickable { })
                }
                Column(modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Başar Çelebi", fontSize = 20.sp, fontFamily = googlesansbold)
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = "Computer Engineering", fontSize = 16.sp, fontFamily = googlesans)
                    Spacer(modifier = Modifier.height(12.dp))



                }

        }
        Spacer(modifier = Modifier.height(15.dp))
        val lazyItems = ProfileData.ProfileOptionsData()
        LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(5.dp)){
            items(ProfileData.ProfileOptionsData().size){ index ->
                ProfileData.ProfileOptionsData()
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .border(4.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                    .clickable {
                        when(index){
                            0 -> navController.navigate("account")
                            1 -> navController.navigate("security")
                            2 -> navController.navigate("about")
                            3 -> navController.navigate("login")
                        }
                    }


                ) {

                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {


                        Column {
                            Icon(imageVector = lazyItems[index].icon, contentDescription = null, modifier = Modifier.padding(start = 10.dp).size(36.dp))
                        }
                        Column(
                            modifier = Modifier.padding(start = 5.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val name = lazyItems[index].name
                            Text(text = "$name", fontFamily = googlesansbold)
                            val description = lazyItems[index].description
                            Text(text = "$description", fontFamily = googlesans, fontSize = 12.sp)
                        }




                    }

                }


            }


        }
    }
}



@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()

}