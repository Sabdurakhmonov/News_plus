package uz.assh_abdurakhmonov.newsplus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.assh_abdurakhmonov.newsplus.domian.repository.MenuRepository
import uz.assh_abdurakhmonov.newsplus.domian.repository.impl.MenuRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @[Binds ViewModelScoped]
    fun bindsMenuRepository(impl: MenuRepositoryImpl): MenuRepository
}