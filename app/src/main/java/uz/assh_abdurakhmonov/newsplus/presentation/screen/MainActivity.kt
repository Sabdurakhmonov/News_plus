package uz.assh_abdurakhmonov.newsplus.presentation.screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import androidx.work.BackoffPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.assh_abdurakhmonov.newsplus.navigation.NavigationHandler
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.HomeScreen
import uz.assh_abdurakhmonov.newsplus.presentation.worker.CustomWorker
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val workerRequest = PeriodicWorkRequestBuilder<CustomWorker>(
            repeatInterval = 12,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        ).setBackoffCriteria(
            backoffPolicy = BackoffPolicy.LINEAR,
            duration = Duration.ofSeconds(5)
        ).build()

        enableEdgeToEdge()
        setContent {
            LaunchedEffect(key1 = "key") {
                val workManager = WorkManager.getInstance(applicationContext)
                workManager.enqueue(workerRequest)
            }

            Navigator(screen = HomeScreen){ navigator ->
                navigationHandler.screenState.onEach {
                    it.invoke(navigator)
                }.launchIn(lifecycleScope)
                CurrentScreen()
            }
        }
    }

}