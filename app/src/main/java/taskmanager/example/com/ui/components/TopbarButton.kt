package taskmanager.example.com.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun TopbarButton(text:String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text(text)
    }

}