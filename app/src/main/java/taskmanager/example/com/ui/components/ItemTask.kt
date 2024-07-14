package taskmanager.example.com.ui.components


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import taskmanager.example.com.ui.viewmodels.HomeViewModel


@Composable
fun ItemTask(id:Int, title:String,description: String, status: String, date: String, time: String,  viewModel:HomeViewModel, navController:NavController){
    var isOpen by remember { mutableStateOf(false) }
    val changeOpenButton = { isOpen = !isOpen }

    if (isOpen) {
        BodyTask(id, title, description, status, date, time,  viewModel, navController, changeOpenButton)
    }else{
        HeadTask(title, changeOpenButton)
    }
}

@Composable
fun HeadTask(title: String, changeOpenButton:()->Unit){
    Row(
        modifier = Modifier
            .clickable { changeOpenButton() }
            .padding(vertical = 4.dp)
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
fun BodyTask(id:Int,
             title: String,
             description: String,
             status: String,
             date: String,
             time: String,
             viewModel:HomeViewModel,
             navController:NavController,
             changeOpenButton:()->Unit
){

    Column(modifier = Modifier
        .padding(vertical = 8.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(1.dp, Color(0xFFDFDFDF)),
            shape = RoundedCornerShape(12.dp)
        )){

        Row( modifier = Modifier
            .padding( bottom = 2.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "")
            TextButton(onClick = { changeOpenButton() }) {
                Text(text = "Close")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row( modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "Titulo")
            Text(text = title)
        }

        Row( modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "Descrição")
            Text(text = description)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row( modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "Status")
            Text(text = status)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row( modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "Data")
            Text(text = date)
        }
        Row( modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Text(text = "Hora")
            Text(text = time)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "")
            Row() {
                TextButton(onClick = { navController.navigate("update/${id}")}) {
                    Text(text = "ALTERAR")
                }
                TextButton(onClick = {
                    viewModel.deleteById(id)
                    viewModel.getAllByStatus(status)
                }) {
                    Text(text = "DELETAR")
                }
            }
    }   }
}