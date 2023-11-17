package com.basarcelebi.khas_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.basarcelebi.khas_app.data.SubjectData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNextButtonClicked: () -> Unit)
{
    val context = LocalContext.current
    val openUrlLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .size(75.dp)
            .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .clickable{
                        val url = "https://www.khas.edu.tr/"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        openUrlLauncher.launch(intent)}
                    ,
                painter = painterResource(R.drawable.khas_logo),
                contentDescription = null
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(75.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), onClick = onNextButtonClicked) {
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
                            Text(text = "Başar Çelebi", fontSize = 16.sp)

                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row {
                            Text(text = "Computer Engineering", fontSize = 12.sp)

                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row {
                            Column {
                                Text(text = "5. Semester", fontSize = 9.sp)

                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column(modifier = Modifier.padding(end = 8.dp)) {

                                Text(text = "GNO: 3,27", fontSize = 9.sp)

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
                Text(text = "Bugünkü Ders Programın", fontSize = 18.sp)

            }
            val lazyItems = SubjectData.SubjectDatas()
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)){
                items(SubjectData.SubjectDatas().size){index ->
                    SubjectData.SubjectDatas()
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(start = 10.dp, end = 10.dp)
                        ,onClick = onNextButtonClicked
                    ) {

                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                            Column {
                                Row(
                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val subjectName = lazyItems[index].subjectName
                                    Text(text = "$subjectName")
                                }

                            }
                            Column(modifier = Modifier.padding(end = 12.dp)) {
                                Row(
                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val subjectTime = lazyItems[index].time
                                    Text(text = "$subjectTime")
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
                Text(text = "Hava Durumu", fontSize = 18.sp)

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .clickable { onNextButtonClicked() }
                    .clip(RoundedCornerShape(20.dp))
                    .padding(3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(96.dp)
                            .padding(16.dp)
                            .background(Color.Transparent),
                        painter = painterResource(id = R.drawable.parcali_bulutlu),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "18.2°",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Istanbul",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
                   /* LazyRow(content = )*/ /*---- TODO -----*/
        }
    }
}









@Preview
@Composable
fun MainScreenPreview()
{

    MainScreen {

    }

}