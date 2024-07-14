package taskmanager.example.com.ui.viewmodels


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import taskmanager.example.com.repositories.TaskRepositoryImpl

class UpdateViewModel(private val repository: TaskRepositoryImpl) : ViewModel() {

    var dataTask by mutableStateOf(DataTask(
        id = 0,
        title = "",
        description = "",
        status = "1",
        time = "",
        data = ""
    ))

    private val _selectedStatus = mutableStateOf(
        listOf(false, false, false)
    )

    val selectedStatus: MutableState<List<Boolean>> = _selectedStatus

    fun changeSelectedStatus(status: List<Boolean>) {
        _selectedStatus.value = status
    }

    fun clear() {
        dataTask = DataTask(
            id = 0,
            title = "",
            description = "",
            status = "1",
            time = "",
            data = ""
        )

        changeSelectedStatus(listOf(false, false, false))
    }

    fun setData(id:Int) {

        viewModelScope.launch {
            val task: TaskEntity = repository.getById(id)

            dataTask = DataTask(
                id = task.id,
                title = task.title,
                description = task.description,
                status = task.status,
                time = task.time,
                data = task.data
            )

            when(task.status){
                "1" -> changeSelectedStatus(listOf(true, false, false))
                "2" -> changeSelectedStatus(listOf(false, true, false))
                "3" -> changeSelectedStatus(listOf(false, false, true))
            }

        }




    }

    fun update(
        id: Int,
        title: String,
        description: String,
        data: String,
        time: String,
        status: String
    ) {
        viewModelScope.launch {
            repository.update(
                TaskModel(
                    id = id,
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
