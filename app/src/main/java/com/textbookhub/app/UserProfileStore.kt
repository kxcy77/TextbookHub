package com.textbookhub.app

import android.content.Context

object UserProfileStore {
    private const val PREFS_NAME = "textbookhub_user_profile"
    private const val KEY_FULL_NAME = "full_name"
    private const val KEY_STUDENT_NUMBER = "student_number"
    private const val KEY_INSTITUTION = "institution"
    private const val KEY_EMAIL = "email"

    fun save(
        context: Context,
        fullName: String,
        studentNumber: String,
        institution: String,
        email: String
    ) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_FULL_NAME, fullName)
            .putString(KEY_STUDENT_NUMBER, studentNumber)
            .putString(KEY_INSTITUTION, institution)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun load(context: Context): UserProfile {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return UserProfile(
            fullName = prefs.getString(KEY_FULL_NAME, "Student User").orEmpty(),
            studentNumber = prefs.getString(KEY_STUDENT_NUMBER, "Student number not set").orEmpty(),
            institution = prefs.getString(KEY_INSTITUTION, "Institution not set").orEmpty(),
            email = prefs.getString(KEY_EMAIL, "Email not set").orEmpty()
        )
    }
}

data class UserProfile(
    val fullName: String,
    val studentNumber: String,
    val institution: String,
    val email: String
)
