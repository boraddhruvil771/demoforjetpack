package com.example.demoforjetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.demoforjetpack.navigation.NavGraph
import com.example.demoforjetpack.ui.theme.DemoForJetpackTheme
import com.example.demoforjetpack.ui.theme.SimpleNavComposeAppTheme
import com.example.demoforjetpack.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoForJetpackTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(viewModel()) }
            composable(BottomNavItem.Search.route) { SearchScreen(viewModel()) }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable("map") { MapScreen() }

        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        PostList(viewModel = viewModel)
    }
}

@Composable
fun SearchScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PhotosList(viewModel = viewModel)
    }
}


@Composable
fun ProfileScreen() {
    SimpleNavComposeAppTheme {
        val navController = rememberNavController()
        NavGraph(navController)
    }
}

@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Map Screen")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoForJetpackTheme {
        MainScreen()
    }
}

@Composable
fun PhotosList(viewModel: MainViewModel) {
    val photos by viewModel.postspic

    // Handle empty state
    if (photos.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No Photos Available", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        LazyColumn {
            items(photos) { photo ->
                Card(
                    onClick = {
                        Log.d("test click item id is:", photo.id.toString())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        photo.url?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier.size(128.dp)
                            )
                        }
                        photo.id?.let {
                            Text(
                                text = "Post ID: $it", style = MaterialTheme.typography.bodySmall
                            )
                        }
                        photo.title?.let {
                            Text(
                                text = "Title: $it",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        photo.thumbnailUrl?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier.size(128.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    // Ensure this only runs once
    DisposableEffect(Unit) {
        viewModel.getPhotos()
        onDispose {}
    }
}

@Composable
fun PostList(viewModel: MainViewModel) {
    val posts by viewModel.posts

    // Handle empty state
    if (posts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No Posts Available", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        LazyColumn {
            items(posts) { post ->
                Card(
                    onClick = {
                        Log.d("test click item id is:", post.id.toString())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        post.userId?.let {
                            Text(
                                text = "User ID: $it", style = MaterialTheme.typography.bodySmall
                            )
                        }
                        post.id?.let {
                            Text(
                                text = "Post ID: $it", style = MaterialTheme.typography.bodySmall
                            )
                        }
                        post.title?.let {
                            Text(
                                text = "Title: $it",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        post.body?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }

    // Ensure this only runs once
    DisposableEffect(Unit) {
        viewModel.getPosts()
        onDispose {}
    }
}
