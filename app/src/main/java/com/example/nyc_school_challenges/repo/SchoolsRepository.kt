package com.example.nyc_school_challenges.repo


import com.example.nyc_school_challenges.domain.HighSchoolResponse
import com.example.nyc_school_challenges.domain.SATScoreResponse
import com.example.nyc_school_challenges.domain.SchoolModel
import com.example.nyc_school_challenges.network.OpenDataAPI

class SchoolsRepository(private val api: OpenDataAPI) {

    private var highSchools: List<HighSchoolResponse>? = null
    private var satScores: List<SATScoreResponse>? = null

//    fun getHighSchools(callback : SchoolsCallback){
//        val url = "s3k6-pzi2.json"
//        val fetchBody = fetch(url)
//        try {
//            highSchools = Gson().fromJson(fetchBody, Array<HighSchoolResponse>::class.java).toList()
//            val result = fetchSchools(highSchools, satScores)
//            if(!result.isEmpty()){
//                callback.onSuccess(result)
//            }
//        }
//        catch (e: Exception){
//            callback.onFailure(e)
//        }
//
//    }

//    fun fetch(url : String) : String {
//        val url = URL("https://data.cityofnewyork.us/resource/$url")
//        val json = url.readText()
//        return json
//    }
//
//    fun getSATScores(callback : SchoolsCallback){
//        val url = "f9bf-2cp4.json"
//        val fetchBody = fetch(url)
//        try {
//            satScores = Gson().fromJson(fetchBody, Array<SATScoreResponse>::class.java).toList()
//            val result = fetchSchools(highSchools, satScores)
//            if(!result.isEmpty()){
//                callback.onSuccess(result)
//            }
//        }
//        catch (e: Exception){
//            callback.onFailure(e)
//        }
//    }

    fun fetchSchools(highSchools: List<HighSchoolResponse>?, satScores: List<SATScoreResponse>?): List<SchoolModel> {
        if(highSchools == null || satScores == null) return listOf()
        val schoolsMap = mutableMapOf<String, SATScoreResponse>()
        for (satScore in satScores) {
            schoolsMap[satScore.dbn ?: ""] = satScore
        }
        return highSchools.map {
            SchoolModel(school = it, satScores = schoolsMap[it.dbn])
        }
    }

    suspend fun getSchoolModel(callback : SchoolsCallback){
        try {
            highSchools = api.highSchools()
            satScores = api.satScores()
            val result = fetchSchools(highSchools, satScores)
            if(result.isNotEmpty()){
                callback.onSuccess(result)
            }
        }
        catch (e: Exception){
            callback.onFailure(e)
        }
    }
    interface SchoolsCallback {
        fun onSuccess(schools: List<SchoolModel>)
        fun onFailure(e: Throwable)
    }
}
