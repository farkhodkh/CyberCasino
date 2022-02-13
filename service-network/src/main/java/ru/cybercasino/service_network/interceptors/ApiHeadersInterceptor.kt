package ru.cybercasino.service_network.interceptors

import okhttp3.Interceptor
import okhttp3.Request

/**
 * The implementation of the [Interceptor] which adds static API headers.
 *
 **/
class ApiHeadersInterceptor : HeaderInterceptor() {
    override suspend fun Request.Builder.setHeaders() {
        header(HeaderKeyAccept, HeaderValueAccept)
        header(HeaderKeyContentType, HeaderValueContentType)
    }
}

private const val HeaderKeyAccept = "accept"
private const val HeaderKeyContentType = "Content-Type"
private const val HeaderValueAccept = "application/json"
private const val HeaderValueContentType = "application/json"