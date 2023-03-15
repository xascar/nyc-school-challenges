package dev.xascar.nycschools.network

import dev.xascar.nycschools.domain.HighSchoolResponse
import dev.xascar.nycschools.domain.SATScoreResponse
import retrofit2.http.GET

interface OpenDataAPI {

    @GET("s3k6-pzi2.json")
    suspend fun highSchools(): List<HighSchoolResponse>

    @GET("f9bf-2cp4.json")
    suspend fun satScores(): List<SATScoreResponse>

}