package id.ratabb.quran.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.QuranRepository
import data.QuranRepositoryImpl

@Suppress("unused") // <- false flag ;(
@Module
@InstallIn(ViewModelComponent::class)
interface RepoProvide {

    @Binds
    @ViewModelScoped
    fun bindSurahRepo(impl: QuranRepositoryImpl): QuranRepository
}
