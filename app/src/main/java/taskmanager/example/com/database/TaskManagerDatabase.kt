package taskmanager.example.com.database

import androidx.room.Database
import androidx.room.RoomDatabase
import taskmanager.example.com.database.dao.TaskDao
import taskmanager.example.com.database.entities.TaskEntity


@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskManagerDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
