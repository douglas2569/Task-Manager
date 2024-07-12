package taskmanager.example.com.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.repositories.TaskRepositoryImpl


class CreateViewModel(private val repository: TaskRepositoryImpl) : ViewModel() {

//    private val _task = mutableStateOf(
//        TaskModel(title = "", description = "", data = "", time = "", status = "")
//    )
//
//    val task: MutableState<TaskModel> = _task

    private val _selectedStatus = mutableStateOf(
        listOf(true, false, false)
    )

    val selectedStatus: MutableState<List<Boolean>> = _selectedStatus

    fun changeSelectedStatus(status: List<Boolean>) {
        _selectedStatus.value = status
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
        }
    }

}
