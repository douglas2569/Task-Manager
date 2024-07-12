package taskmanager.example.com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val status: String,
    val time: String,
    val data: String
)