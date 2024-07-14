package taskmanager.example.com.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.ui.components.Bottombar
import taskmanager.example.com.ui.components.ItemTask
import taskmanager.example.com.ui.components.TaskStatusControlButtons
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.components.TopbarButton
import taskmanager.example.com.ui.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val viewModel = koinViewModel<HomeViewModel>()
    val tasks by viewModel.tasks.observeAsState(emptyList())
    val searchValue by viewModel.searchValue.observeAsState("")

    var visibilitySearch by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(viewModel.selectedItem) {
        viewModel.getAllByStatus(viewModel.selectedItem.toString())
    }

    Scaffold(
        topBar = {
            Topbar(
                { TopbarButton("", {}) },
                { Text(text = "Projetos", fontSize = 20.sp, fontWeight = FontWeight.W400) },
                { TopbarButton("Search", { visibilitySearch = !visibilitySearch }) },
                scrollBehavior
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = 18.dp,
                        end = 18.dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                TaskStatusControlButtons(
                    { viewModel.selectedItem = 1 },
                    { viewModel.selectedItem = 2 },
                    { viewModel.selectedItem = 3 },
                    viewModel
                )

                Spacer(modifier = Modifier.height(16.dp))
                if (visibilitySearch) {
                    OutlinedTextField(
                        value = searchValue,
                        onValueChange = { newValue ->
                            viewModel.updateSearchValue(newValue)
                        },
                        placeholder = { Text("Search") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                ListTasks(viewModel.selectedItem, tasks, viewModel, navController)
            }
        },
        bottomBar = {
            Bottombar("Criar Task", { navController.navigate("create") })
        }
    )
}


@Composable
fun NoTasksMessage(title:String, description: String){
    Text(text = title)
    Text(text = description)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ListTasks(status:Int, tasks: List<TaskEntity>, viewModel:HomeViewModel, navController:NavController){

    if (tasks.isEmpty()) {
        when (status) {
            1 -> NoTasksMessage("Nada aqui por agora", "Aqui que você encontrar seus projetos pendentes")

            2 -> NoTasksMessage("Nada aqui por agora", "Aqui que você encontrar seus projetos em andamento")

            3 ->  NoTasksMessage("Nada aqui por agora", "Aqui que você encontrar seus projetos finalizados")
        }

    }else{
        when (status) {
            1 -> {
                Text(text = "Lista")
                Text(text = "Itens marcados como pendentes")
            }

            2 -> {
                Text(text = "Lista")
                Text(text = "Itens marcados como em andamento")
            }

            3 -> {
                Text(text = "Lista")
                Text(text = "Itens marcados como concluidos")
            }
        }

        tasks.forEach { task ->
            ItemTask(task.id, task.title, task.description, task.status, task.time, task.data,  viewModel, navController)
        }
    }

}