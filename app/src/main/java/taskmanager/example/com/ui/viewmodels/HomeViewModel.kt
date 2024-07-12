package taskmanager.example.com.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.repositories.TaskRepositoryImpl

class HomeViewModel(
    private val repository: TaskRepositoryImpl
) : ViewModel() {

    var selectedItem by mutableIntStateOf(3)

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val tasks: LiveData<List<TaskEntity>> = _tasks

    init {
        getAllByStatus("pending")
    }

    fun deleteAllCard() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun getAll() {
        viewModelScope.launch {
            _tasks.value = repository.getAll()
        }
    }

    fun getAllByStatus(status: String) {
        viewModelScope.launch {
            _tasks.value = repository.getAllByStatus(status)
        }
    }

    fun insert(
        title: String,
        description: String,
        data: String,
        time: String,
        status: String
    ) {
        viewModelScope.launch {
            repository.insert(
                TaskModel(
                    title = title,
                    description = description,
                    data = data,
                    time = time,
                    status = status
                )
            )
            getAllByStatus(status)
        }
    }



    /*
    suspend fun deleteAllCard(){
        repository.deleteAll()
    }

    suspend fun getAll(): Flow<List<TaskEntity>> {
        return repository.getAll()
    }

    suspend fun getAllByStatus(status: String): Flow<TaskEntity> {
        return repository.getAllByStatus(status)
    }

    suspend fun insert(
        title: String,
        description: String,
        data: String,
        time: String,
        status: String
    ){
        repository.insert(
            TaskModel(
                title = title,
                description = description,
                data = data,
                time = time,
                status = status
            )
        )
    }

     */

}