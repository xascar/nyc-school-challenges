package com.example.nyc_school_challenges.domain

import android.net.Uri
import java.util.*

data class SchoolModel(
    val school: HighSchoolResponse?= null,
    val satScores: SATScoreResponse?= null
){
    val schoolName: String = "${school?.schoolName} (${school?.dbn})"

    val addressString: String get() = "${school?.primaryAddressLine1}, ${school?.city}, ${school?.stateCode}, ${school?.zip}"
    val website = school?.website

    val webSiteUrl: Uri
    get() = if (website?.lowercase(Locale.ROOT)?.startsWith("http") == true) {
        Uri.parse(website)
    } else {
        Uri.parse("http://$website")
    }
    val email = school?.schoolEmail
    val emailUrl : Uri? get() = email?.let { Uri.parse("mailto:$it") }
    val phone: String get() = school?.phoneNumber ?: ""
    val phoneURL: Uri get() = phone.let { Uri.parse("tel:$it") }

}