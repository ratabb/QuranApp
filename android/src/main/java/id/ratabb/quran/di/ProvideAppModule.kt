package id.ratabb.quran.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import items.QuranDb
import items.dao.AyahDao
import items.dao.SurahDao
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
@InstallIn(SingletonComponent::class)
object ProvideAppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuranDb =
        QuranDb.build(context) { createFromAsset("quran.db") }

    @Provides
    @Singleton
    fun provideSurahDao(quranDb: QuranDb): SurahDao = quranDb.getSurahDao()

    @Provides
    @Singleton
    fun provideAyahDao(quranDb: QuranDb): AyahDao = quranDb.getAyahDao()
}
