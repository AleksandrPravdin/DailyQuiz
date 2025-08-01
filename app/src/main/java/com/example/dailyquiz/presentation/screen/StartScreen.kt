package com.example.dailyquiz.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.component.WelcomeCard

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(100.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(23.dp),
            colors = ButtonDefaults.buttonColors(

            ), modifier = Modifier
                .height(46.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(id = R.string.story),
                    color = MaterialTheme.colorScheme.background
                )
                Spacer(Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.history_icon),
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    contentDescription = null
                )
            }
        }

        Spacer(Modifier.height(150.dp))

        Image(
            painter = painterResource(id = R.drawable.dqlogo),
            modifier = Modifier
                .width(300.dp)
                .height(80.dp),
            contentDescription = null
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            WelcomeCard({ navController.navigate("filter_screen") })
        }
    }
}