package com.basarcelebi.khas_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen()
{
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .size(75.dp)
            .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.khas_logo), contentDescription = null)

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(75.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Column(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .padding(6.dp)) {
                        Image(painter = painterResource(id = R.drawable.khas_logo), contentDescription = null)

                    }
                    Column(modifier = Modifier.padding(top=6.dp)) {
                        Row {
                            Text(text = "Başar Çelebi")

                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row {
                            Text(text = "Computer Engineering")

                        }

                    }

                }


            }

        }

    }


}

@Preview
@Composable
fun MainScreenPreview()
{
    MainScreen()
}