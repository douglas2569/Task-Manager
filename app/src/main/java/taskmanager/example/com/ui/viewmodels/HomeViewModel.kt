package taskmanager.example.com.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.repositories.TaskRepositoryImpl

class HomeViewModel(
    private val repository: TaskRepositoryImpl
) : ViewModel() {

    suspend fun getAll(): Flow<List<TaskEntity>> {
        return repository.getAll()
    }

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