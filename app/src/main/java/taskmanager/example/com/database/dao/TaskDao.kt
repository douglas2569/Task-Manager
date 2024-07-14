package taskmanager.example.com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import taskmanager.example.com.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    suspend fun findAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getById(id: Int): TaskEntity

    @Query("SELECT * FROM TaskEntity WHERE status = :status")
    suspend fun getAllByStatus(status: String): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE status = :status AND (title LIKE '%' || :value || '%' OR description LIKE '%' || :value || '%')")
    suspend fun getAllByStatusAndTitleOrDescription(status: String, value: String): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: TaskEntity)

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM TaskEntity")
    suspend fun deleteAll()

}
