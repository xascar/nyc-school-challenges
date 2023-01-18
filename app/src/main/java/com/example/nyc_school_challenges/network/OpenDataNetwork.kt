package com.example.nyc_school_challenges.network

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
            .baseUrl("http://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesNYCOpenDataAPI(retrofit: Retrofit): OpenDataAPI {
        return retrofit.create(OpenDataAPI::class.java)
    }


}