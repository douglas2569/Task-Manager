package taskmanager.example.com.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import taskmanager.example.com.ui.components.Bottombar
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.components.TopbarButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    taskId: Int,
    navController: NavController

){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        topBar = {
            Topbar(
                { TopbarButton("Cancel", {}) },
                { TopbarButton("New Task", {}) },
                { TopbarButton("Clear", {}) },
                scrollBehavior)
        },
        content = {
                paddingValues ->
            Column {
                Text(text = "taskId23: $taskId")
            }
        },
        bottomBar = {
            Bottombar("Save", {  })
        }
    )


}
