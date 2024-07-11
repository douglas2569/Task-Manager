package taskmanager.example.com.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
   leftButton: @Composable () -> Unit,
   centerButton: @Composable () -> Unit,
   rightButton: @Composable () -> Unit,
   scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            centerButton()

        },
        navigationIcon = {
            leftButton()
        },
        actions = {
            rightButton()
        },
        scrollBehavior = scrollBehavior,
    )
}

