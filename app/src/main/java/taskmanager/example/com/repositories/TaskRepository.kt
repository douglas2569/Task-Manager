package taskmanager.example.com.repositories

import taskmanager.example.com.database.dao.TaskDao
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers.IO

class TaskRepository(private val dao: TaskDao) {
    val getAll get() = dao.findAll()

    fun getById(id: String){
        dao.getById(id)
    }

    suspend fun insert(todo: TaskModel) = withContext(IO) {
        dao.insert(todo.toEntity())
    }

    suspend fun delete(id: String) = withContext(IO){
        dao.delete(id)
    }
}

fun TaskModel.toEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    data = this.data,
    status = this.status
)