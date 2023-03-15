package dev.xascar.nycschools.repo


import dev.xascar.nycschools.domain.HighSchoolResponse
import dev.xascar.nycschools.domain.SATScoreResponse
import dev.xascar.nycschools.domain.SchoolModel
import dev.xascar.nycschools.network.OpenDataAPI

class SchoolsRepository(private val api: OpenDataAPI) {

    private var highSchools: List<HighSchoolResponse>? = null
    private var satScores: List<SATScoreResponse>? = null

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
