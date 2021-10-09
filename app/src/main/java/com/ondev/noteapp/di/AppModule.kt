package com.ondev.noteapp.di

import android.content.Context
import com.ondev.noteapp.data.local.NoteDatabase
import com.ondev.noteapp.data.local.NoteRepository
import com.ondev.noteapp.data.local.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        NoteDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePlaceDao(database: NoteDatabase): NoteDao = database.noteDao()

    @Singleton
    @Provides
    fun provideRepository(
        noteDao: NoteDao
    ) = NoteRepository(noteDao = noteDao)

}