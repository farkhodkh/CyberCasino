package ru.cybercasino.feature.auth

import androidx.annotation.Keep


/**
 * The enumeration of client status values.
 */
@Keep
enum class ClientStatus {
    /**
     * User is not logged in, the login menu should be shown when application is starting.
     */
    NOT_LOGGED_IN,

    /**
     * User is logged in, the main application screen should be shown when application is starting.
     */
    LOGGED_IN,

    /**
     * User is not logged in, but want to continue as guest.
     * The main application screen should be shown when application is starting. However force login may be necessary
     * when user is trying to perform logged-user-specific actions.
     */
    GUEST,

    /**
     * User has send verification code
     */
    VERIFICATION
}