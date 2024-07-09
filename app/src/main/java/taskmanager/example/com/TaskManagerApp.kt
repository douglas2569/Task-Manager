package taskmanager.example.com

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.screens.CreateTaskScreen
import taskmanager.example.com.ui.screens.HomeScreen
import taskmanager.example.com.ui.screens.UpdateTaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskManagerApp() {
    Scaffold(
        topBar = { /*Topbar()*/ },
        content = {
            paddingValues ->
                Column {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {

                        composable("home") {
                            HomeScreen(navController)
                        }

                        composable("create") {
                            CreateTaskScreen()
                        }
                        composable("update/{taskId}") { backstackEntry ->
                            backstackEntry.arguments?.getString("taskId")
                                ?.let { taskId ->
                                    UpdateTaskScreen(taskId.toInt())
                                }

                        }

                        composable("delete/{taskId}") { backstackEntry ->
                            backstackEntry.arguments?.getString("taskId")?.toInt()
                            //executar o delete
                        }
                    }
                }
        },
        bottomBar = { Text(text = "Bottombar") }
    )

}
