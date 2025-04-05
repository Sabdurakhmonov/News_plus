package uz.assh_abdurakhmonov.newsplus.presentation.screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.assh_abdurakhmonov.newsplus.navigation.NavigationHandler
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.HomeScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val workerRequest = OneTimeWorkRequestBuilder<CustomWorker>().setBackoffCriteria(
//            backoffPolicy = BackoffPolicy.LINEAR,
//            duration = Duration.ofSeconds(5)
//        ).build()
//        val workManager = WorkManager.getInstance(applicationContext)
//        workManager.enqueue(workerRequest)
//
//        val workerRequest1 = PeriodicWorkRequestBuilder<CustomWorker>(
//            repeatInterval = 6,
//            repeatIntervalTimeUnit = TimeUnit.HOURS
//        ).setBackoffCriteria(backoffPolicy = BackoffPolicy.LINEAR,5,TimeUnit.SECONDS).build()
//
//
//        val infos =  WorkManager.getInstance(applicationContext).getWorkInfosFlow(
//            WorkQuery.Builder.fromUniqueWorkNames(
//                listOf("worker")
//            ).build()
//        )
//
//        fun setWorker(workerRequest:PeriodicWorkRequest){
//            WorkManager.getInstance(applicationContext)
//            workManager.enqueue(workerRequest)
//        }
//
//        lifecycleScope.launch {
//            infos.collect{workerList->
//                workerList.forEach {
//                    if(it.state == WorkInfo.State.SUCCEEDED){
//                        setWorker(workerRequest1)
//                    }
//                }
//            }
//        }

        enableEdgeToEdge()
        setContent {
            Navigator(screen = HomeScreen) { navigator ->
                navigationHandler.screenState.onEach {
                    it.invoke(navigator)
                }.launchIn(lifecycleScope)
                CurrentScreen()
            }
        }
    }

}