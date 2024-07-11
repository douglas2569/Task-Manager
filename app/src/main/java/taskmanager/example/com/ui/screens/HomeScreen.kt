package taskmanager.example.com.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import taskmanager.example.com.ui.components.Bottombar
import taskmanager.example.com.ui.components.TaskStatusControlButtons
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.components.TopbarButton
import taskmanager.example.com.ui.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen( navController: NavController){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()


        Scaffold(
            topBar = {
                Topbar(
                    { TopbarButton("", {}) },
                    { TopbarButton("Projects", {}) },
                    { TopbarButton("search", {}) },
                    scrollBehavior)
             },
            content = {
                    innitPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = innitPadding.calculateTopPadding(),
                            bottom = innitPadding.calculateBottomPadding(),
                            start = 18.dp,
                            end = 18.dp
                        )
                )
                {
                    TaskStatusControlButtons(
                        {},
                        {},
                        {}
                    )
                    listTasks("finished" )
                }



            },
            bottomBar = {
                Bottombar("Create Task", { navController.navigate("create") })
            }
        )
   // }

    //navController.navigate("update/1")
}

@Composable
fun listTasks(statusControllButton: String = ""):Boolean{

    when(statusControllButton){
        "pending" ->
            ListTasksPending()
        "inprocess" ->
            ListTasksInProcess()
        "finished" ->
            ListTasksFinished()
       else ->
           NoTasksMessage()

    }

    return true
}

@Composable
fun NoTasksMessage(){
    Text(text = "Nada aqui por agora")
    Text(text = "Aqui que você encontrar seus projetos finalizados")
}

@Composable
fun ListTasksPending(){
    Text(text = "Lista")
    Text(text = "Itens marcados como pendentes")
}

@Composable
fun ListTasksInProcess(){
    Text(text = "Lista")
    Text(text = "Itens marcados como em processo")
}

@Composable
fun ListTasksFinished(){
    Text(text = "Lista")
    Text(text = "Itens marcados como concluidos")



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