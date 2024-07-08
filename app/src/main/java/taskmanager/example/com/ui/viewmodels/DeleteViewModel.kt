package taskmanager.example.com.ui.viewmodels

import androidx.lifecycle.ViewModel
import taskmanager.example.com.repositories.TaskRepository

class DeleteViewModel(
    private val repository: TaskRepository
) : ViewModel() {

}