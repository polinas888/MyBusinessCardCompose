package com.example.mybusinesscardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybusinesscardcompose.ui.theme.MyBusinessCardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBusinessCardComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBusinessCard()
                }
            }
        }
    }

    @Composable
    fun CreateBusinessCard() {
        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(modifier = Modifier)
                Divider()
                ProfileInfo()
                CreateButton()
            }
        }
    }

    @Composable
    private fun CreatePortfolioContent() {
        Box {
            Surface(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                ProjectsRecyclerView(listOf("Project1", "Project2", "Project3", "Project4"))
            }
        }
    }

    @Composable
    private fun ProjectsRecyclerView(data: List<String>) {
        LazyColumn(modifier = Modifier.padding(5.dp)) {
            items(data) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    shape = RectangleShape,
                    elevation = 4.dp
                ) {
                    Row(modifier = Modifier.padding(15.dp)) {
                        CreateImageProfile(modifier = Modifier.size(100.dp))
                        Column(
                            modifier = Modifier.padding(7.dp).align(Alignment.CenterVertically)
                        ) {
                            Text(text = item, style = MaterialTheme.typography.h6)
                            Text(text = "This is great project")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CreateButton() {
        val showProjects = remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            showProjects.value = !showProjects.value
        }) {
            Text(text = "Portfolio")
        }
        if (showProjects.value) {
            CreatePortfolioContent()
        } else {
            Box {
            }
        }
    }

    @Composable
    private fun ProfileInfo() {
        Text(
            text = "Polina S.",
            style = MaterialTheme.typography.h4,
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Android developer",
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = "polina@gmail.com",
            style = MaterialTheme.typography.subtitle1,
        )
    }

    @Composable
    private fun CreateImageProfile(modifier: Modifier = Modifier) {
        Surface(
            modifier.size(150.dp).padding(5.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_photo),
                contentDescription = "profile photo",
                modifier.size(135.dp).padding(5.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyBusinessCardComposeTheme {
            CreateBusinessCard()
        }
    }
}


