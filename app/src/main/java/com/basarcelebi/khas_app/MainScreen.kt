package com.basarcelebi.khas_app

import androidx.annotation.StringRes
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.Navigation
import com.basarcelebi.khas_app.data.SubjectData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNextButtonClicked: () -> Unit)
{

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .size(75.dp)
            .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.khas_logo), contentDescription = null, modifier = Modifier.clickable {  })

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(75.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = onNextButtonClicked, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    ,colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
                ){
                    Column(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        ) {
                        Image(painter = painterResource(id = R.drawable.basar_celebi), contentDescription = null, modifier = Modifier.clip(
                            CircleShape))
                    }
                    Column(modifier = Modifier.padding(start = 12.dp, top=3.dp)) {
                        Row {
                            Text(text = "Başar Çelebi", fontSize = 16.sp, color = Color.Black)

                        }
                        Spacer(modifier = Modifier.height(1.dp))
                        Row {
                            Text(text = "Computer Engineering", fontSize = 12.sp, color = Color.Black)

                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row {
                            Column {
                                Text(text = "5. Semester", fontSize = 9.sp, color = Color.Black)

                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column(modifier = Modifier.padding(end = 8.dp)) {

                                Text(text = "GNO: 3,27", fontSize = 9.sp, color = Color.Black)

                            }


                        }


                    }



                }



            }


        }
        Column(modifier = Modifier
            .fillMaxSize()
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
        Column(modifier = Modifier.fillMaxSize()) {

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