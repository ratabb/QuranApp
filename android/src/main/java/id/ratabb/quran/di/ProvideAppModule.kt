package id.ratabb.quran.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import items.QuranDb
import items.dao.AyahDao
import items.dao.SurahDao
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
@InstallIn(SingletonComponent::class)
object ProvideAppModule {

    @Provides
    @Singleton
    fun provideJsonFormat(): Json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuranDb =
        Room.databaseBuilder(context, QuranDb::class.java, "quran")
            .createFromAsset("quran.db")
            .build()

    @Provides
    @Singleton
    fun provideSurahDao(quranDb: QuranDb): SurahDao = quranDb.getSurahDao()

    @Provides
    @Singleton
    fun provideAyahDao(quranDb: QuranDb): AyahDao = quranDb.getAyahDao()

    /* @Provides
     @Singleton
     fun providQuranJson(@ApplicationContext context: Context, json: Json): QuranJsonToEntity =
         QuranJsonToEntity(context.assets.open("quran.json"), json)*/
}
