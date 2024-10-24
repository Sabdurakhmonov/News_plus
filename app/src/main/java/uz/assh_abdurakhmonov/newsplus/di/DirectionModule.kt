package uz.assh_abdurakhmonov.newsplus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite.PopularContract
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.favourite.PopularDirection
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu.MenuContract
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.menu.MenuDirection
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification.HotNewsContract
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.notification.HotNewsDirection
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search.SearchContract
import uz.assh_abdurakhmonov.newsplus.presentation.screen.home.search.SearchDirection
import uz.assh_abdurakhmonov.newsplus.presentation.screen.post.PostContract
import uz.assh_abdurakhmonov.newsplus.presentation.screen.post.PostDirection


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds ViewModelScoped]
    fun bindsHotTab(impl: HotNewsDirection): HotNewsContract.Direction

    @[Binds ViewModelScoped]
    fun bindsMenuTab(impl: MenuDirection): MenuContract.Direction

    @[Binds ViewModelScoped]
    fun bindsPopular(impl: PopularDirection): PopularContract.Direction

    @[Binds ViewModelScoped]
    fun bindsSearch(impl: SearchDirection): SearchContract.Direction

    @[Binds ViewModelScoped]
    fun bindsPost(impl: PostDirection): PostContract.Direction
}