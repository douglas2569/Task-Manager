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
import taskmanager.example.com.ui.viewmodels.UpdateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    taskId: Int,
    navController: NavController

){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val updateViewModel = koinViewModel<UpdateViewModel>()
    updateViewModel.setData(taskId)

    Scaffold(
        topBar = {
            Topbar(
                { TopbarButton("Cancelar", { navController.navigate("home") }) },
                { Text(text = "Atualizar Task", fontSize = 20.sp, fontWeight = FontWeight.W400) },
                { TopbarButton("Limpar", {updateViewModel.clear()}) },
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
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemFormField("Titulo", updateViewModel)
                }

                Row(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemFormField("Descrição", updateViewModel)
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
                        SwitchStatusTask(updateViewModel)
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ItemFormField("Data", updateViewModel)
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ItemFormField("Hora", updateViewModel)
                    }
                }
            }


        },
        bottomBar = {
            Bottombar("Salvar", {
                updateViewModel.update(
                    updateViewModel.dataTask.id,
                    updateViewModel.dataTask.title,
                    updateViewModel.dataTask.description,
                    updateViewModel.dataTask.data,
                    updateViewModel.dataTask.time,
                    updateViewModel.dataTask.status
                )
                updateViewModel.clear()
            })
        }
    )


}


@Composable
fun ItemFormField(title: String, updateViewModel: UpdateViewModel) {
    var isOpen by remember { mutableStateOf(false) }
    val changeOpenButton = { isOpen = !isOpen }
    Column {
        HeadItem(title, changeOpenButton)
        if (isOpen)
            BodyItem(title, updateViewModel)
    }
}



@Composable
fun HeadItem(title: String, changeOpenButton: () -> Unit){
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
fun BodyItem(title: String, updateViewModel: UpdateViewModel) {
    when (title) {
        "Titulo" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = updateViewModel.dataTask.title,
                    onValueChange = { updateViewModel.dataTask = updateViewModel.dataTask.copy(title = it) },
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
                    value = updateViewModel.dataTask.description,
                    onValueChange = { updateViewModel.dataTask = updateViewModel.dataTask.copy(description = it) },
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
                    value = updateViewModel.dataTask.time,
                    onValueChange = { updateViewModel.dataTask = updateViewModel.dataTask.copy(time = it) },
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
                    value = updateViewModel.dataTask.data,
                    onValueChange = { updateViewModel.dataTask = updateViewModel.dataTask.copy(data = it) },
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
fun SwitchStatusTask( viewModel: UpdateViewModel) {

    SwitchButton(
        {
            viewModel.changeSelectedStatus(listOf(true, false, false))
            viewModel.dataTask.status = "1"
        },
        ButtonDefaults.buttonColors(containerColor = if (viewModel.selectedStatus.value[0]) BluePrimary else BlueTertiary),
        "PENDENTE",
        if (viewModel.selectedStatus.value[0]) Color.White else BluePrimary
    )

    SwitchButton(
        {
            viewModel.changeSelectedStatus(listOf(false, true, false))
            viewModel.dataTask.status = "2"
        },
        ButtonDefaults.buttonColors(containerColor = if (viewModel.selectedStatus.value[1]) BluePrimary else BlueTertiary),
        "EM PROGRESSO",
        if (viewModel.selectedStatus.value[1]) Color.White else BluePrimary
    )

    SwitchButton(
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
fun SwitchButton(
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
