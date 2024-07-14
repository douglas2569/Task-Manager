package taskmanager.example.com.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import taskmanager.example.com.ui.viewmodels.HomeViewModel

@Composable
fun TaskStatusControlButtons(
        onClickPending: () -> Unit = {},
        onClickInProcess: () -> Unit = {},
        onClickFinished: () -> Unit = {},
        viewModel: HomeViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFEEEEEE))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextButton(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(if (viewModel.selectedItem == 1) Color.White else Color(0xFFEEEEEE)),
            onClick = { onClickPending() },

        ) {
            Text("Pendentes", fontSize = 14.sp, color = if (viewModel.selectedItem == 1) Color.Black else Color.Gray)
        }

        TextButton(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(if (viewModel.selectedItem == 2) Color.White else Color(0xFFEEEEEE)),
            onClick = { onClickInProcess() }
        ) {
            Text("Em processo", fontSize = 14.sp, color = if (viewModel.selectedItem == 2) Color.Black else Color.Gray)
        }

        TextButton(
            modifier = Modifier
                .clip(RoundedCornerShape(22.dp))
                .background(if (viewModel.selectedItem == 3) Color.White else Color(0xFFEEEEEE)),
            onClick = { onClickFinished() }
        ) {
            Text("Feito", fontSize = 14.sp, fontWeight = FontWeight(weight = 400), color = if (viewModel.selectedItem == 3) Color.Black else Color.Gray)
        }
    }

  }