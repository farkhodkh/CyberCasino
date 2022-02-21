-if class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema
-keepnames class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema
-if class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema
-keep class ru.cybercasino.feature.auth.api.responses.LoginResponseSchemaJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-if class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema
-keepclassmembers class ru.cybercasino.feature.auth.api.responses.LoginResponseSchema {
    public synthetic <init>(boolean,java.lang.String,ru.cybercasino.feature.auth.api.responses.UserResponseSchema,java.lang.String,java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
