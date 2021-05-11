package id.ratabb.quran.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ratabb.quran.pref.DayNightPref
import id.ratabb.quran.pref.DayNightPrefImpl
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

    private val Context.dataStore by preferencesDataStore("pref")

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun provideDayNightPref(impl: DayNightPrefImpl): DayNightPref = impl
}
