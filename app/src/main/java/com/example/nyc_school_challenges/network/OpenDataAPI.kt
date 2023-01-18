package com.example.nyc_school_challenges.network

import com.example.nyc_school_challenges.domain.HighSchoolResponse
import com.example.nyc_school_challenges.domain.SATScoreResponse
import retrofit2.http.GET

interface OpenDataAPI {

    @GET("s3k6-pzi2.json")
    suspend fun highSchools(): List<HighSchoolResponse>

    @GET("f9bf-2cp4.json")
    suspend fun satScores(): List<SATScoreResponse>

//    @GET("s3k6-pzi2.json")
//    fun highSchools(): Call<List<HighSchoolResponse>>
//
//    @GET("f9bf-2cp4.json")
//    fun satScores(): Call<List<SATScoreResponse>>
}