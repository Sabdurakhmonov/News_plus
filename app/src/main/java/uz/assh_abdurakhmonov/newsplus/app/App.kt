package uz.assh_abdurakhmonov.newsplus.app

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dagger.hilt.android.HiltAndroidApp
import uz.assh_abdurakhmonov.newsplus.domian.repository.MenuRepository
import uz.assh_abdurakhmonov.newsplus.presentation.worker.CustomWorker
import javax.inject.Inject

@HiltAndroidApp
class App:Application(),Configuration.Provider{
    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() =Configuration.Builder()
            .setWorkerFactory(customWorkerFactory)
            .build()

}

class CustomWorkerFactory @Inject constructor(
    val repository: MenuRepository
):WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = CustomWorker(repository,appContext,workerParameters)
}