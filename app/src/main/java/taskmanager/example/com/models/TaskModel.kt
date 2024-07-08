package taskmanager.example.com.models

data class TaskModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val data: Int,
    val status: String,
)
