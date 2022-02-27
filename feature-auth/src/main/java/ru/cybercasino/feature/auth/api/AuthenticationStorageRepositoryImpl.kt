package ru.cybercasino.feature.auth.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.cybercasino.core.network.security.EncryptionResult
import ru.cybercasino.core.network.security.SecurityHelper
import ru.cybercasino.feature.auth.ClientStatus
import ru.cybercasino.feature.auth.api.responses.UserResponseSchema

/**
 * The implementation of the [AuthenticationStorageRepository] with [DataStore].
 * @param security The [SecurityHelper] to encrypt / decrypt the token value.
 * @param context The context to get [DataStore] instance
 *
 **/
@Suppress("TooManyFunctions")
class AuthenticationStorageRepositoryImpl(
    private val context: Context,
    private val security: SecurityHelper,
) : AuthenticationStorageRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    private val gson = Gson()

    override suspend fun setStatus(value: ClientStatus) {
        context.dataStore.edit { preferences ->
            preferences[KEY_STATUS] = value.name
        }
    }

    override suspend fun getStatus(): Flow<ClientStatus> =
        context.dataStore.data
            .map { preferences ->
                preferences[KEY_STATUS]?.let { ClientStatus.valueOf(it) }
                    ?: ClientStatus.NOT_LOGGED_IN
            }

    override suspend fun setToken(value: String?) =
        setEncrypted(KEY_TOKEN, value)

    override suspend fun getToken(): Flow<String?> =
        getEncrypted(KEY_TOKEN)

    override suspend fun setLoginEmail(value: String?) {
        setEncrypted(KEY_LOGIN_EMAIL, value)
    }

    override suspend fun getLoginEmail(): Flow<String?> = getEncrypted(KEY_LOGIN_EMAIL)

    override suspend fun setLoginName(value: String?) {
        setEncrypted(KEY_LOGIN_NAME, value)
    }

    override suspend fun getLoginPhone(): Flow<String?> = getEncrypted(KEY_PHONE)

    override suspend fun setLoginPhone(value: String?) {
        setEncrypted(KEY_PHONE, value)
    }

    override suspend fun getLoginName(): Flow<String?> =
        getEncrypted(KEY_LOGIN_NAME)

    override suspend fun setPass(value: String?) {
        setEncrypted(KEY_PASS, value)
    }

    override suspend fun getPass(): Flow<String?> = getEncrypted(KEY_PASS)

    override suspend fun getVerificationCode(): Flow<String?> = getEncrypted(KEY_VERIFICATION_CODE)

    override suspend fun setVerificationCode(value: String?) {
        setEncrypted(KEY_VERIFICATION_CODE, value)
    }

    override suspend fun setUser(value: UserResponseSchema?) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER] = gson.toJson(value)
        }
    }

    override suspend fun getUser(): Flow<UserResponseSchema?> =
        context.dataStore.data
            .map { preferences ->
                preferences[KEY_USER]?.let { gson.fromJson(it, UserResponseSchema::class.java) }
                    ?: UserResponseSchema()
            }


    override suspend fun getLoginInfo(): Flow<LoginInfo?> = combine(
        getStatus(),
        getLoginEmail(),
        getLoginPhone(),
        getPass(),
        getVerificationCode()
    ) { status, email, phone, pass, verificationCode->

        if (status in listOf(ClientStatus.LOGGED_IN, ClientStatus.VERIFICATION)) {
            LoginInfo(email, phone, pass, verificationCode, status)
        } else {
            null
        }
    }

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
                preferences[key]?.let { security.decrypt(EncryptionResult.deserialize(it)) } ?: null
            }
}

class LoginInfo(
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val verificationCode: String? = null,
    val status: ClientStatus
)

private const val PREFERENCES_NAME = "cz.kosik.library.auth.info"

private val KEY_PASS = stringPreferencesKey("password")
private val KEY_VERIFICATION_CODE = stringPreferencesKey("verification_code")
private val KEY_STATUS = stringPreferencesKey("status")
private val KEY_PHONE = stringPreferencesKey("phone")
private val KEY_TOKEN = stringPreferencesKey("access_token")
private val KEY_LOGIN_EMAIL = stringPreferencesKey("login_email")
private val KEY_LOGIN_NAME = stringPreferencesKey("login_name")
private val KEY_USER = stringPreferencesKey("user")