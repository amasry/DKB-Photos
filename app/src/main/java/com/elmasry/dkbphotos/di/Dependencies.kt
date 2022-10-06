package com.elmasry.dkbphotos.di

import com.elmasry.dkbphotos.data.PhotosApi
import com.elmasry.dkbphotos.data.PhotosRepository
import com.elmasry.dkbphotos.data.PhotosRepositoryImpl
import com.elmasry.dkbphotos.mapper.PhotoMapper
import com.elmasry.dkbphotos.photos.PhotosViewModel
import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object Dependencies {

    fun get() = module {
        single {
            createRetrofit()
        }

        single<PhotosRepository> {
            PhotosRepositoryImpl(photosApi = get<Retrofit>().create(PhotosApi::class.java), photoMapper = PhotoMapper())
        }

        viewModel {
            PhotosViewModel(photosRepository = get())
        }
    }

    private fun createMoshi(): Moshi = Moshi.Builder().build()

    private fun createRetrofit(
        baseUrl: String = BASE_URL,
        client: OkHttpClient = createOkHttpClient(),
        moshi: Moshi = createMoshi()
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) // <-- default subscribeOn()
            ).client(client)
            .baseUrl(baseUrl)
            .build()
    }

    private fun createOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
