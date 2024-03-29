package com.basarcelebi.khas_app.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.basarcelebi.khas_app.R
import com.basarcelebi.khas_app.data.SubjectData
import com.basarcelebi.khas_app.model.BaseModel
import com.basarcelebi.khas_app.ui.theme.googlesans
import com.basarcelebi.khas_app.ui.theme.googlesansbold
import com.basarcelebi.khas_app.ui.theme.russuFont
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun MainScreen(context: Context,
               navController: NavController,
               locationKey: String="318251",
               locationName: String="Istanbul",
               country: String="Türkiye",
               viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel())
{
    val hourlyForecasts by viewModel.hourlyForecast.collectAsState()
    val gpaData = listOf(3.5f, 3.8f, 3.9f, 4.0f, 3.7f)
    val semesterLabels = listOf("Fall 2021", "Spring 2022", "Fall 2022", "Spring 2023", "Fall 2023")

    LaunchedEffect(Unit){
        viewModel.getHourlyForecast(locationKey)
    }
    val openUrlLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .size(75.dp)
            .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .clickable {
                        val url = "https://www.khas.edu.tr/"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        openUrlLauncher.launch(intent)
                    }
                ,
                painter = painterResource(R.drawable.khas_logo),
                contentDescription = null
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(75.dp)) {
            Card(modifier = Modifier.fillMaxWidth().clickable { navController.navigate(Screens.ProfileScreen.screen) }) {
                Row{
                    Column(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .padding(5.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.basar_celebi), contentDescription = null, modifier = Modifier.clip(
                            CircleShape))
                    }
                    Column(modifier = Modifier.padding(top=6.dp)) {
                        Row {
                            Text(text = "Başar Çelebi", fontSize = 16.sp, fontFamily = googlesansbold)

                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row {
                            Text(text = "Computer Engineering", fontSize = 12.sp, fontFamily = googlesansbold)

                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row {
                            Column {
                                Text(text = "5. Semester", fontSize = 9.sp, fontFamily = googlesans)

                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column(modifier = Modifier.padding(end = 8.dp)) {

                                Text(text = "GNO: 3,27", fontSize = 9.sp, fontFamily = googlesans)

                            }


                        }


                    }

                }








            }


        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "Bugünkü Ders Programın", fontSize = 18.sp, fontFamily = googlesansbold)

            }
            val lazyItems = SubjectData.SubjectDatas()
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)){
                items(SubjectData.SubjectDatas().size){index ->
                    SubjectData.SubjectDatas()
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(start = 10.dp, end = 10.dp)

                    ) {

                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                            Column {
                                Row(
                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val subjectName = lazyItems[index].subjectName
                                    Text(text = "$subjectName", fontFamily = googlesansbold)
                                }

                            }
                            Column(modifier = Modifier.padding(end = 12.dp)) {
                                Row(
                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val subjectTime = lazyItems[index].time
                                    Text(text = "$subjectTime", fontFamily = googlesansbold)
                                }

                            }

                        }

                    }


                }


            }


        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(5.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "Hava Durumu", fontSize = 18.sp, fontFamily = googlesansbold)

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screens.WeatherScreen.screen) }
                    .clip(RoundedCornerShape(20.dp))
                    .padding(3.dp)
            ) {
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
                    val data = hourlyForecasts as BaseModel.Success
                    val temp = data.data.firstOrNull()?.temperature


                    Column(modifier = Modifier.fillMaxWidth().padding(5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$locationName", fontSize = 30.sp, color = Color.White, fontFamily = googlesansbold)
                            Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${temp?.value}°",
                            fontWeight = FontWeight.Bold,
                            fontSize = 60.sp,
                            color = Color.White,
                            fontFamily = russuFont
                        )

                        }



                }
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
                    Loading()

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(modifier = Modifier.padding(start = 2.dp),text = "Hourly Forecasts:", fontSize = 18.sp, color = Color.White, fontFamily = googlesansbold)
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
                    val data = hourlyForecasts as BaseModel.Success
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                        items(data.data){forecast->
                            Column(modifier = Modifier
                                .size(90.dp, 120.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = SimpleDateFormat("H a").format(Date(forecast.epochDateTime*1000)), color = Color.Gray, fontFamily = googlesansbold)
                                AsyncImage(modifier = Modifier.size(60.dp),
                                    model = ImageRequest.Builder(LocalContext.current).data("https://developer.accuweather.com/sites/default/files/${forecast.weatherIcon.fixIcon()}-s.png").build(),
                                    contentScale = ContentScale.Fit,
                                    contentDescription = null)
                                Text(text = forecast.temperature.value.toString()+"°", color = Color.White, fontFamily = googlesansbold)
                            }

                        }
                    }

                }
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
                    Loading()

                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(5.dp)){
            GPAGraph(gpaData, semesterLabels)
        }
    }
}



fun Int.fixIcon():String{
    return if(this>9){
        toString()
    }else {
        "0${this}"
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(color = Color.White)
    }

}


@Composable
fun GPAGraph(gpaData: List<Float>, semesterLabels: List<String>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        // Calculate the maximum GPA value
        val maxGpa = 4f

        // Calculate the height of each bar
        val barHeight = size.height / maxGpa

        // Draw the bars
        gpaData.forEachIndexed { index, gpa ->
            val x = (index + 1) * (size.width / (gpaData.size + 1))
            val y = size.height - (gpa * barHeight)
            drawLine(
                color = Color.Blue,
                start = Offset(x, size.height),
                end = Offset(x, y),
                strokeWidth = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        // Draw the semester labels
        semesterLabels.forEachIndexed { index, label ->
            val x = (index + 1) * (size.width / (gpaData.size + 1))
            val y = size.height + 10.dp.toPx()
            drawContext.canvas.nativeCanvas.drawText(
                label,
                x,
                y,
                android.graphics.Paint().apply {
                    textSize = 12.sp.toPx()
                    color = android.graphics.Color.BLACK
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview()
{



}