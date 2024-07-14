package taskmanager.example.com.repositories

import taskmanager.example.com.database.dao.TaskDao
import taskmanager.example.com.database.entities.TaskEntity
import taskmanager.example.com.models.TaskModel
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAll(): List<TaskEntity>
    suspend fun getById(id: Int): TaskEntity
    suspend fun getAllByStatus(status: String):List<TaskEntity>
    suspend fun insert(task: TaskModel)
    suspend fun deleteById(id: Int)
    suspend fun update(task: TaskModel)
}

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {

    override suspend fun getAll():List<TaskEntity> {
        return dao.findAll()
    }

    override suspend fun getById(id: Int): TaskEntity{
       return dao.getById(id)
    }

    override suspend fun getAllByStatus(status: String): List<TaskEntity>{
        return dao.getAllByStatus(status)
    }

    override suspend fun insert(task: TaskModel) = withContext(IO) {
        dao.insert(task.toEntity())
    }

    override suspend fun update(task: TaskModel) = withContext(IO) {
        dao.update(task.toEntity())
    }

    override suspend fun deleteById(id: Int) = withContext(IO){
        dao.delete(id)
    }

    suspend fun deleteAll() = withContext(IO){
        dao.deleteAll()
    }

}

fun TaskModel.toEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    status = this.status,
    time = this.time,
    data = this.data,

)