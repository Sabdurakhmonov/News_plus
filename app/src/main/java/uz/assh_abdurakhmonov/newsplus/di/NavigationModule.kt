package uz.assh_abdurakhmonov.newsplus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigationDispatcher
import uz.assh_abdurakhmonov.newsplus.navigation.AppNavigator
import uz.assh_abdurakhmonov.newsplus.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigator(dispatcher: AppNavigationDispatcher): AppNavigator
    @Binds
    fun bindNavigationHandler(dispatcher: AppNavigationDispatcher): NavigationHandler
}