package com.bookmyshow.common_ui.utils.network

import android.util.Log
import com.bookmyshow.common_ui.utils.network.ExceptionConstants.CONNECT_EXCEPTION
import com.bookmyshow.common_ui.utils.network.ExceptionConstants.SOCKET_TIME_OUT_EXCEPTION
import com.bookmyshow.common_ui.utils.network.ExceptionConstants.UNKNOWN_HOST_EXCEPTION
import com.bookmyshow.common_ui.utils.network.ExceptionConstants.UNKNOWN_NETWORK_EXCEPTION
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionConstants {
    // Network Messages
    const val SOCKET_TIME_OUT_EXCEPTION = "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
    const val UNKNOWN_NETWORK_EXCEPTION = "An unexpected error has occurred. Please check your network connection and try again."
    const val CONNECT_EXCEPTION = "Could not connect to the server. Please check your internet connection and try again."
    const val UNKNOWN_HOST_EXCEPTION = "Couldn't connect to the server at the moment. Please try again in a few minutes."

}
suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkStatus<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return NetworkStatus.Success(response.body())
            }
        }
        return NetworkStatus.Error(response.message())
    } catch (e: Exception) {
        Log.d("safeApiCall", "safeApiCall:  ${e.localizedMessage}")
        return when (e) {
            is ConnectException -> {
                NetworkStatus.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                NetworkStatus.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}