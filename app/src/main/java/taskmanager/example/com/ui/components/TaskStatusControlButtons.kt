package taskmanager.example.com.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskStatusControlButtons(
        onClickPending: () -> Unit = {},
        onClickInProcess: () -> Unit = {},
        onClickFinished: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        TextButton(
            onClick = { onClickPending() }
        ) {
            Text("Pendentes", fontSize = 14.sp)
        }

        TextButton(
            onClick = { onClickInProcess() }
        ) {
            Text("Em processo", fontSize = 14.sp)
        }

        TextButton(
            onClick = { onClickFinished() }
        ) {
            Text("Concluidos", fontSize = 14.sp)
        }


}  }