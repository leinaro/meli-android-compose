package com.leinaro.meli.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.leinaro.meli.data.local.PreferencesKeys.SELECTED_SITE_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_SETTINGS = "user_settings"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val SELECTED_SITE_ID = stringPreferencesKey("selected_site_id")
}

class UserDataStore @Inject constructor(
    private val context: Context,
) {
    private val selectedSiteFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            val selectedSite = preferences[SELECTED_SITE_ID]
            selectedSite
        }

    fun getSelectedSiteId(): Flow<String?> {
        return selectedSiteFlow
    }

    suspend fun setSelectedSite(siteId: String) {
        context.dataStore.edit { settings ->
            settings[SELECTED_SITE_ID] = siteId
        }
    }
}
