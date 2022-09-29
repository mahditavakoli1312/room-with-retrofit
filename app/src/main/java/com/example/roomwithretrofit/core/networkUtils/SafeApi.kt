package com.example.roomwithretrofit.core.networkUtils


//const val APP_ERROR_MESSAGE = "Application error while try to get respone"
suspend fun <T> safeApiCall(api: suspend () -> T, localDataForErrorState: T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(data = api.invoke())
    } catch (exception: Exception) {
        when (exception) {
            is ServerException -> {
                ResultWrapper.Failure(
                    severMessage = exception.serverMessage,
                    code = exception.code,
                    data = localDataForErrorState
                )
            }

            else -> {
                ResultWrapper.AppError(
                    appMessage = exception.message.toString(),
                    data = localDataForErrorState
                )
            }
        }
    }
}