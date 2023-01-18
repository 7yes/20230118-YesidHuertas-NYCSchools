package com.example.y20230118_yesidhuertas_nycschools.di

import com.example.y20230118_yesidhuertas_nycschools.dataaccess.database.IDatabaseRepository
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.network.ISchoolApiRepository
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.network.SchoolAPI
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.network.SchoolApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providerOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): SchoolAPI =
        Retrofit.Builder()
            .baseUrl(SchoolAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolAPI::class.java)

    @Provides
    fun providesSchoolApiRepository(
        schoolAPI: SchoolAPI,
        databaseRepository: IDatabaseRepository
    ): ISchoolApiRepository = SchoolApiRepository(schoolAPI, databaseRepository)

}

private const val REQUEST_TIMEOUT = 30L