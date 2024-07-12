package taskmanager.example.com


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import taskmanager.example.com.ui.screens.CreateTaskScreen
import taskmanager.example.com.ui.screens.HomeScreen
import taskmanager.example.com.ui.screens.UpdateTaskScreen

@Composable
fun TaskManagerApp() {
    val navController = rememberNavController()

     NavHost(navController = navController, startDestination = "create") {

           composable("home") {
                HomeScreen(
                    navController
                )
           }

            composable("create") {
                CreateTaskScreen(
                    navController
                )
            }

            composable("update/{taskId}") { backstackEntry ->
                backstackEntry.arguments?.getString("taskId")
                    ?.let { taskId ->
                        UpdateTaskScreen(
                            taskId.toInt(),
                            navController
                        )
                    }

            }

            composable("delete/{taskId}") { backstackEntry ->
                backstackEntry.arguments?.getString("taskId")?.toInt()
            }
     }

}

