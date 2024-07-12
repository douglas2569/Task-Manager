package taskmanager.example.com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import taskmanager.example.com.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    suspend fun findAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getById(id: String): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE status = :status")
    suspend fun getAllByStatus(status: String): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TaskEntity)

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM TaskEntity")
    suspend fun deleteAll()

}
