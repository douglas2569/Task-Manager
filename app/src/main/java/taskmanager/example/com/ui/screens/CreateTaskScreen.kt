package taskmanager.example.com.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import taskmanager.example.com.ui.components.Bottombar
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.components.TopbarButton
import taskmanager.example.com.ui.theme.BluePrimary
import taskmanager.example.com.ui.theme.BlueTertiary
import taskmanager.example.com.ui.viewmodels.CreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val createViewModel = koinViewModel<CreateViewModel>()

    Scaffold(
        topBar = {
            Topbar(
                { TopbarButton("Cancelar", { navController.navigate("home") }) },
                { Text(text = "Nova Tasks", fontSize = 20.sp, fontWeight = FontWeight.W400) },
                { TopbarButton("Limpar", { createViewModel.clear() }) },
                scrollBehavior)
        },
        content = { innitPadding ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = innitPadding.calculateTopPadding(),
                        bottom = innitPadding.calculateBottomPadding(),
                        start = 18.dp,
                        end = 18.dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemFormFieldCreate("Titulo", createViewModel)
                }

                Row(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemFormFieldCreate("Descrição", createViewModel)
                }

                Column(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Status")
                    }

                    Row(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SwitchStatusTaskCreate(createViewModel)
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ItemFormFieldCreate("Data", createViewModel)
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ItemFormFieldCreate("Hora", createViewModel)
                    }
                }
            }
        },
        bottomBar = {
            Bottombar("Criar", {
                createViewModel.insert(
                    createViewModel.dataTask.title,
                    createViewModel.dataTask.description,
                    createViewModel.dataTask.data,
                    createViewModel.dataTask.time,
                    createViewModel.dataTask.status
                )

                createViewModel.clear()
            })
        }
    )
}


@Composable
fun ItemFormFieldCreate(title: String, createViewModel: CreateViewModel) {
    var isOpen by remember { mutableStateOf(false) }
    val changeOpenButton = { isOpen = !isOpen }
    Column {
        HeadItemCreate(title, changeOpenButton)
        if (isOpen)
            BodyItemCreate(title, createViewModel)
    }
}



@Composable
fun HeadItemCreate(title: String, changeOpenButton: () -> Unit){
    Row(
        modifier = Modifier
            .clickable { changeOpenButton() }
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color(0xFFDFDFDF)),
                shape = RoundedCornerShape(12.dp)
            )

    ) {
        Text(modifier = Modifier.padding(16.dp), text = title)
    }
}


@Composable
fun BodyItemCreate(title: String, createViewModel: CreateViewModel) {
    when (title) {
        "Titulo" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = createViewModel.dataTask.title,
                    onValueChange = { createViewModel.dataTask = createViewModel.dataTask.copy(title = it) },
                    label = { Text("Titulo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        "Descrição" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = createViewModel.dataTask.description,
                    onValueChange = { createViewModel.dataTask = createViewModel.dataTask.copy(description = it) },
                    label = { Text("Descrição") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        "Hora" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = createViewModel.dataTask.time,
                    onValueChange = { createViewModel.dataTask = createViewModel.dataTask.copy(time = it) },
                    label = { Text("Hora") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        "Data" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = createViewModel.dataTask.data,
                    onValueChange = { createViewModel.dataTask = createViewModel.dataTask.copy(data = it) },
                    label = { Text("Data") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun SwitchStatusTaskCreate( viewModel:CreateViewModel ) {

    SwitchButtonCreate(
        {
            viewModel.changeSelectedStatus(listOf(true, false, false))
            viewModel.dataTask.status = "1"
        },
        ButtonDefaults.buttonColors(containerColor = if (viewModel.selectedStatus.value[0]) BluePrimary else BlueTertiary),
        "PENDENTE",
        if (viewModel.selectedStatus.value[0]) Color.White else BluePrimary
    )

    SwitchButtonCreate(
        {
            viewModel.changeSelectedStatus(listOf(false, true, false))
            viewModel.dataTask.status = "2"
        },
        ButtonDefaults.buttonColors(containerColor = if (viewModel.selectedStatus.value[1]) BluePrimary else BlueTertiary),
        "EM PROGRESSO",
        if (viewModel.selectedStatus.value[1]) Color.White else BluePrimary
    )

    SwitchButtonCreate(
        {
            viewModel.changeSelectedStatus(listOf(false, false, true))
            viewModel.dataTask.status = "3"
        },
        ButtonDefaults.buttonColors(containerColor = if (viewModel.selectedStatus.value[2]) BluePrimary else BlueTertiary),
        "TERMINADO",
        if (viewModel.selectedStatus.value[2]) Color.White else BluePrimary
    )


}

@Composable
fun SwitchButtonCreate(
    onClick: () -> Unit,
    colorButton: ButtonColors,
    text: String,
    textColor: Color
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(32.dp),
        colors = colorButton
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.W600, fontSize = 10.sp)
    }
}