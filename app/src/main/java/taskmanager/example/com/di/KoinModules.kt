package taskmanager.example.com.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import taskmanager.example.com.database.TaskManagerDatabase
import taskmanager.example.com.repositories.TaskRepository

val appModule = module {
    singleOf(::TaskRepository)
    //viewModelOf(::HomeViewModel)
}

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TaskManagerDatabase::class.java, "taskmanager_database.db"
        ).build()
    }
    single { get<TaskManagerDatabase>().taskDao() }
}
