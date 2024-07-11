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

@Composable
fun TaskStatusControlButtons(
        onClickPending: () -> Unit = {},
        onClickInProcess: () -> Unit = {},
        onClickFinished: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        TextButton(
            onClick = { onClickPending() }
        ) {
            Text("Pending")
        }

        TextButton(
            onClick = { onClickInProcess() }
        ) {
            Text("In Process")
        }

        TextButton(
            onClick = { onClickFinished() }
        ) {
            Text("Finished")
        }


}  }