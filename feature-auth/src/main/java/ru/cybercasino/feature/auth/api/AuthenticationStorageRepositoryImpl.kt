package ru.cybercasino.feature.auth.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.cybercasino.core.network.security.EncryptionResult
import ru.cybercasino.core.network.security.SecurityHelper
import ru.cybercasino.feature.auth.ClientStatus

/**
 * The implementation of the [AuthenticationStorageRepository] with [DataStore].
 * @param security The [SecurityHelper] to encrypt / decrypt the token value.
 * @param context The context to get [DataStore] instance
 *
 **/
@Suppress("TooManyFunctions")
class AuthenticationStorageRepositoryImpl(
    private val context: Context,
    private val security: SecurityHelper
) : AuthenticationStorageRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    override suspend fun setStatus(value: ClientStatus) {
        context.dataStore.edit { preferences ->
            preferences[KEY_STATUS] = value.name
        }
    }

    override suspend fun getStatus(): Flow<ClientStatus> =
        context.dataStore.data
            .map { preferences ->
                preferences[KEY_STATUS]?.let { ClientStatus.valueOf(it) } ?: ClientStatus.NOT_LOGGED_IN
            }

    override suspend fun setToken(value: String?) =
        setEncrypted(KEY_TOKEN, value)

    override suspend fun getToken(): Flow<String?> =
        getEncrypted(KEY_TOKEN)

    override suspend fun setLoginEmail(value: String?) {
        setEncrypted(KEY_LOGIN_EMAIL, value)
    }

    override suspend fun getLoginEmail(): Flow<String?> =
        getEncrypted(KEY_LOGIN_EMAIL)

    override suspend fun setLoginName(value: String?) {
        setEncrypted(KEY_LOGIN_NAME, value)
    }

    override suspend fun getLoginName(): Flow<String?> =
        getEncrypted(KEY_LOGIN_NAME)

    private suspend fun setEncrypted(key: Preferences.Key<String>, value: String?) {
        context.dataStore.edit { preferences ->
            if (!value.isNullOrEmpty()) {
                preferences[key] = EncryptionResult.serialize(security.encrypt(value))
            } else {
                preferences.remove(key)
            }
        }
    }

    private suspend fun getEncrypted(key: Preferences.Key<String>): Flow<String?> =
        context.dataStore.data
            .map { preferences ->
                preferences[key]?.let { security.decrypt(EncryptionResult.deserialize(it)) }
            }
}

private const val PREFERENCES_NAME = "cz.kosik.library.auth.info"

private val KEY_STATUS = stringPreferencesKey("status")
private val KEY_UUID = stringPreferencesKey("uuid")
private val KEY_TOKEN = stringPreferencesKey("access_token")
private val KEY_LOGIN_EMAIL = stringPreferencesKey("login_email")
private val KEY_LOGIN_NAME = stringPreferencesKey("login_name")