package com.example.demoforjetpack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.demoforjetpack.ui.theme.SimpleNavComposeAppTheme
import com.example.demoforjetpack.common.DefaultButton

@Composable
fun HomeScreen(
    navigateToProfile: (Int, Boolean) -> Unit,
    navigateToSearch: (Any) -> Unit,
    popBackStack: () -> Unit,
    popUpToLogin: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", fontSize = 40.sp)

        DefaultButton(
            text = "Profile",
            onClick = { navigateToProfile(7, true) }
        )

        DefaultButton(
            text = "Search",
            onClick = { navigateToSearch(arrayOf("d", "h", "r", "u", "v", "i", "l", "-", "1")) }
        )

        DefaultButton(
            text = "Back",
            onClick = popBackStack
        )

        DefaultButton(
            text = "Log Out",
            onClick = popUpToLogin
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SimpleNavComposeAppTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            HomeScreen(
                navigateToProfile = { _, _ -> },
                navigateToSearch = {},
                popBackStack = {},
                popUpToLogin = {})
        }
    }
}