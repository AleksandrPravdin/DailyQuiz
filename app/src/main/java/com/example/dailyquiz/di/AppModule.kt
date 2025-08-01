package com.example.dailyquiz.di

import com.example.dailyquiz.data.remote.api.QuizApiService
import com.example.dailyquiz.data.remote.mapper.QuizMapper
import com.example.dailyquiz.data.repository.QuizRepositoryImpl
import com.example.dailyquiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideQuizMapper(): QuizMapper {
        return QuizMapper()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(
        apiService: QuizApiService,
        mapper: QuizMapper
    ): QuizRepository {
        return QuizRepositoryImpl(apiService, mapper)
    }

}