package taskmanager.example.com.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
           scrollBehavior: TopAppBarScrollBehavior,
           scope: CoroutineScope,
           navController: NavController
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            TopbarButton("") { navController.navigate("home") }
        },
        navigationIcon = {

        },
        actions = {

        },
        scrollBehavior = scrollBehavior,
    )
}

