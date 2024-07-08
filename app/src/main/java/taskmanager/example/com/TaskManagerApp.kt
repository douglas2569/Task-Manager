package taskmanager.example.com

import android.app.Application
import taskmanager.example.com.di.appModule
import taskmanager.example.com.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TaskManagerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TaskManagerApp)
            modules(
                appModule,
                storageModule
            )
        }
    }
}