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
import taskmanager.example.com.ui.components.Bottombar
import taskmanager.example.com.ui.components.Topbar
import taskmanager.example.com.ui.components.TopbarButton

data class Task(
    var titulo: String = "",
    var descricao: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen( navController: NavController){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var task by remember { mutableStateOf(Task()) }

    Scaffold(
        topBar = {
            Topbar(
                { TopbarButton("Cancelar", { navController.navigate("home") }) },
                { Text(text = "Nova Tasks", fontSize = 20.sp, fontWeight = FontWeight.W400) },
                { TopbarButton("Limpar", {}) },
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
            )
            {
                Spacer(modifier = Modifier.height(18.dp))

                Row( modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween ){
                    ItemFormField("Titulo")
                }

                Row( modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween ){
                    ItemFormField("Descrição")
                }

                Column( modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()){

                    Row(modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                    ) {
                        Text(text = "Status")
                    }

                    Row(modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "PENDENTE")
                        Text(text = "EM PROGRESSO")
                        Text(text = "TERMINADO")
                    }

                    Row( modifier = Modifier
                        .padding(bottom = 2.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween ){
                        ItemFormField("Hora")
                    }

                }

            }
        },
        bottomBar = {
            Bottombar("Criar", {  })
        }
    )

}
@Composable
fun ItemFormField(title:String){
    var isOpen by remember { mutableStateOf(false) }
    val changeOpenButton = { isOpen = !isOpen }
    Column {
        HeadItem(title, changeOpenButton)
        if (isOpen)
            BodyItem()
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
fun BodyItem(){
    val textState = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }

}