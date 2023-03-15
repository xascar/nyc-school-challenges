package dev.xascar.nycschools.network

import dev.xascar.nycschools.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//we will add dependency injection from hilt here

@InstallIn(ViewModelComponent::class)
@Module
object OpenDataNetwork {
    //add retrofit client here

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
                ).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesNYCOpenDataAPI(retrofit: Retrofit): OpenDataAPI {
        return retrofit.create(OpenDataAPI::class.java)
    }


}