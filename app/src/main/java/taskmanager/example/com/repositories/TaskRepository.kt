package taskmanager.example.com.repositories

import taskmanager.example.com.database.dao.TaskDao
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAll(): Flow<List<TaskEntity>>
    suspend fun getById(id: String)
    suspend fun insert(task: TaskModel)
    suspend fun delete(id: String)
}

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {

    override suspend fun getAll(): Flow<List<TaskEntity>> {
        return dao.findAll()
    }

    override suspend fun getById(id: String){
        dao.getById(id)
    }

    override suspend fun insert(task: TaskModel) = withContext(IO) {
        dao.insert(task.toEntity())
    }

    override suspend fun delete(id: String) = withContext(IO){
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