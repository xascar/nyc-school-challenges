package com.example.nyc_school_challenges.network

import com.example.nyc_school_challenges.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesNYCOpenDataAPI(retrofit: Retrofit): OpenDataAPI {
        return retrofit.create(OpenDataAPI::class.java)
    }


}