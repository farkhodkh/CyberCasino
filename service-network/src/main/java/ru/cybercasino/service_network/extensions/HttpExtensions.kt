package ru.cybercasino.service_network.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.InputStreamReader
import org.json.JSONObject
import retrofit2.Response
import ru.cybercasino.core.network.common.ResponseSchema

val gson = Gson()

@RequiresApi(Build.VERSION_CODES.N)
inline fun <reified T: ResponseSchema> Response<T>.httpErrorHandler() : ResponseSchema? {
    val response = this

    if (this.isSuccessful) {
        return (response.body() as T).apply {
            this.isSuccessful = true
        }
    } else {
        val errorBodyStr = InputStreamReader(response.errorBody()?.byteStream()).readText()
        try {
            return gson.fromJson(errorBodyStr, T::class.java)
        } catch (ex: Exception) {
            return JSONObject(errorBodyStr)
                .parseDefaultHttpError()
        }
    }
}