package tech.kaustubhdeshpande.kotlinconf27.data.source.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import tech.kaustubhdeshpande.kotlinconf27.util.Constants
import okio.Path.Companion.toPath

class PreferencesDataStoreProviderImpl(private val context: Context) : PreferencesDataStoreProvider {
    override fun providePreferencesDataStore(): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            context.applicationContext
                .filesDir
                .resolve(Constants.PREFERENCES_STORAGE_NAME)
                .absolutePath
                .toPath()
        },
    )
}
