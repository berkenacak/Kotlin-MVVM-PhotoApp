package com.berke.internshipproject.base

import com.berke.internshipproject.model.Result
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.Success(result.body()!!)
            } else {
                /**
                 * can be used if there is response from api
                 *  **/
//                val type = object : TypeToken<ErrorResponse>() {}.type
//                result.errorBody()?.let { errorBody ->
//                    var errorResponse: ErrorResponse? = Gson().fromJson(errorBody.charStream(), type)
//                }

                Result.Error("Not Successful")
            }
        } catch (e: Throwable) {
            e.message?.let { Result.Error(it) } ?: run { Result.Error("Throwable") }
        }
    }
}