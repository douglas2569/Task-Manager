package taskmanager.example.com.ui.viewmodels

import androidx.lifecycle.ViewModel
import taskmanager.example.com.repositories.TaskRepository

class EditViewModel(
    private val repository: TaskRepository
) : ViewModel() {

}