package taskmanager.example.com.ui.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    @SuppressLint("MutableCollectionMutableState")
    private val _expandedStates = mutableStateOf(mutableMapOf<Int, Boolean>())
    val expandedStates: MutableState<MutableMap<Int, Boolean>> = _expandedStates



    init {
        getAllByStatus("pending")
    }


    fun getAllByStatus(status: String) {
        viewModelScope.launch {
            _tasks.value = repository.getAllByStatus(status)
        }
    }

    fun deleteById(id:Int){
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }

}