package taskmanager.example.com.ui.viewmodels

import androidx.lifecycle.ViewModel
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.repositories.TaskRepository

class HomeViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val getAll get() = repository.getAll.toString()

    suspend fun insert(
        title: String,
        description: String,
        data: Int,
        status: String
    ){
        repository.insert(
            TaskModel(
                title = title,
                description = description,
                data = data,
                status = status
            )
        )
    }
}