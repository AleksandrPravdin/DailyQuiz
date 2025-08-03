package com.example.dailyquiz.di

import android.content.Context
import androidx.room.Room
import com.example.dailyquiz.data.local.QuizDatabase
import com.example.dailyquiz.data.local.dao.QuizResultDao
import com.example.dailyquiz.data.remote.api.QuizApiService
import com.example.dailyquiz.data.remote.mapper.QuizMapper
import com.example.dailyquiz.data.repository.QuizPassingRepositoryImpl
import com.example.dailyquiz.data.repository.QuizResultRepositoryImpl
import com.example.dailyquiz.domain.repository.QuizPassingRepository
import com.example.dailyquiz.domain.repository.QuizResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createQuizApiService(): QuizApiService {
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuizDatabase {
        return Room.databaseBuilder(
            context,
            QuizDatabase::class.java,
            "quiz-database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideQuizResultDao(database: QuizDatabase): QuizResultDao {
        return database.quizResultDao()
    }

    @Provides
    @Singleton
    fun provideQuizMapper(): QuizMapper {
        return QuizMapper()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(
        apiService: QuizApiService,
        mapper: QuizMapper
    ): QuizPassingRepository {
        return QuizPassingRepositoryImpl(apiService, mapper)
    }

    @Provides
    @Singleton
    fun provideQuizResultRepository(dao: QuizResultDao): QuizResultRepository {
        return QuizResultRepositoryImpl(dao)
    }

}