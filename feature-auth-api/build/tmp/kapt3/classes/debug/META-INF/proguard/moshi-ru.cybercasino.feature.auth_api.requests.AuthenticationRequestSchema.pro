-if class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema
-keepnames class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema
-if class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema
-keep class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchemaJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-if class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema
-keepclassmembers class ru.cybercasino.feature.auth_api.requests.AuthenticationRequestSchema {
    public synthetic <init>(java.lang.String,java.lang.String,java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
