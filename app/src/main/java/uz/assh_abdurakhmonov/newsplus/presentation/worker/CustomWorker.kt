package uz.assh_abdurakhmonov.newsplus.presentation.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import uz.assh_abdurakhmonov.newsplus.domian.repository.MenuRepository

@HiltWorker
class CustomWorker @AssistedInject constructor(
    @Assisted private val repository: MenuRepository,
    @Assisted context: Context,
    @Assisted params:WorkerParameters
):Worker(context,params) {
    override fun doWork(): Result {
        return Result.failure()
    }
}