package com.example.restaurantapp.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Lab 6: SharedPreferences wrapper for simple key-value settings.
 * Used for non-reactive settings that don't need Flow observation.
 */
class SettingsPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "app_settings"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_NICKNAME = "user_nickname"
        private const val KEY_BACKUP_FILENAME = "backup_filename"
        private const val KEY_HAS_INTERNAL_BACKUP = "has_internal_backup"
        
        const val DEFAULT_BACKUP_FILENAME = "restaurant_backup.txt"
    }

    // Email
    var email: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    // Nickname
    var nickname: String
        get() = prefs.getString(KEY_NICKNAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_NICKNAME, value).apply()

    // Backup filename
    var backupFilename: String
        get() = prefs.getString(KEY_BACKUP_FILENAME, DEFAULT_BACKUP_FILENAME) 
            ?: DEFAULT_BACKUP_FILENAME
        set(value) = prefs.edit().putString(KEY_BACKUP_FILENAME, value).apply()

    // Flag indicating if there's a hidden internal backup
    var hasInternalBackup: Boolean
        get() = prefs.getBoolean(KEY_HAS_INTERNAL_BACKUP, false)
        set(value) = prefs.edit().putBoolean(KEY_HAS_INTERNAL_BACKUP, value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }
}
