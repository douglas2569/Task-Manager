package taskmanager.example.com.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import taskmanager.example.com.ui.components.TopbarButton

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen( navController: NavController){

    Column {

    }
    //navController.navigate("update/1")
}

/*
fun HomeScreen(){
    val viewModel = koinViewModel<HomeViewModel>()
    val scope = rememberCoroutineScope()

    scope.launch {
        try {
            viewModel.getAll().collect { tasks ->
                println("Tasks: $tasks")
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    scope.launch {
        viewModel.insert("Test", "Test", 1, "Test")
    }

}
 */