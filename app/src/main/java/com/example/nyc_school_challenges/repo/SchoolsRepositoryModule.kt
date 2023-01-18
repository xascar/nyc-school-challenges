package com.example.nyc_school_challenges.repo

import com.example.nyc_school_challenges.network.OpenDataAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object SchoolsRepositoryModule {
    @Provides
    fun providesSchoolsRepository(api: OpenDataAPI): SchoolsRepository {
        return SchoolsRepository(api)
    }
}