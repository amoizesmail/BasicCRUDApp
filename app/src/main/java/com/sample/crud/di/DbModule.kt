package com.sample.crud.di

import android.content.Context
import androidx.room.Room
import com.sample.crud.db.ContactDatabase
import com.sample.crud.db.Contact
import com.sample.crud.Constants.CONTACT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ContactDatabase::class.java, CONTACT_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: ContactDatabase) = db.contactDoa()

    @Provides
    fun provideEntity() = Contact()


}