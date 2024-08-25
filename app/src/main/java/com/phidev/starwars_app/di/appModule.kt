package com.phidev.starwars_app.di

import com.phidev.starwars_app.data.CharacterRepository
import com.phidev.starwars_app.data.SearchRepository
import com.phidev.starwars_app.data.SelectedItemDetailRepository
import com.phidev.starwars_app.network.ApiService
import com.phidev.starwars_app.views.main.CharacterViewModel
import com.phidev.starwars_app.views.main.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

val appModule = module {
    single <OkHttpClient>{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(Duration.ofSeconds(30L))
            .writeTimeout(Duration.ofSeconds(30L))
            .build()
    }
    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
    single { CharacterRepository(get()) }
    single { SearchRepository(get()) }
    viewModel { CharacterViewModel(get() , get()) }
    viewModel { SearchViewModel(get() , get()) }
    single { SelectedItemDetailRepository() }
}