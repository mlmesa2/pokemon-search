package com.mlmesa.pokemonsearcher.data.remote

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import com.mlmesa.pokemonsearcher.common.Result
import kotlinx.serialization.SerializationException


suspend inline fun <T> safeApiCall(call: () -> Response<T>): Result<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let { Result.Success(it) }
                ?: Result.Error(Exception("Response body is null"))
        } else {
            Result.Error(HttpException(response))
        }
    } catch (e: HttpException) {
        // Handle HTTP exceptions here. You can parse the response body to get more details, if the API supports it
        Result.Error(e)
    } catch (e: UnknownHostException) {
        // Handle network exceptions here
        Result.Error(e)
    } catch (e: IOException) {
        // Handle other IO exceptions here
        Result.Error(e)
    } catch (e: SerializationException) {
        // Handle JSON parsing exceptions here
        if (e.message?.contains("EOF", ignoreCase = true) == true) {
            // Special handling for EOF exception, which occurs if the body is empty.
            Result.Empty
        } else {
            Result.Error(e)
        }
    }
}