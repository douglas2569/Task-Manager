package taskmanager.example.com.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import taskmanager.example.com.database.TaskManagerDatabase
import taskmanager.example.com.repositories.TaskRepositoryImpl
import taskmanager.example.com.ui.viewmodels.CreateViewModel
import taskmanager.example.com.ui.viewmodels.HomeViewModel
import taskmanager.example.com.ui.viewmodels.UpdateViewModel

val appModule = module {
    singleOf(::TaskRepositoryImpl)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateViewModel)
    viewModelOf(::UpdateViewModel)
}

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TaskManagerDatabase::class.java, "taskmanager_database.db"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<TaskManagerDatabase>().taskDao() }
}
