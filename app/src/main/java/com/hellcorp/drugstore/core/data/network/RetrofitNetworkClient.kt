package com.hellcorp.drugstore.core.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.hellcorp.drugstore.core.data.network.dto.Response
import com.hellcorp.drugstore.core.data.network.request.DrugListSearchRequest
import com.hellcorp.drugstore.core.data.network.request.SingleDrugRequest
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
    private val drugService: DrugService,
    private val context: Context
) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!context.checkInternetReachability()) return Response().apply {
            resultCode = NO_INTERNET_ERROR
        }
        return withContext(Dispatchers.IO) {
            try {
                doRequestInternal(dto)
            } catch (e: IOException) {
                e.printStackTrace()
                Response().apply { resultCode = NO_INTERNET_ERROR }
            } catch (e: HttpException) {
                e.printStackTrace()
                getHttpExceptionResponse()
            } catch (e: RuntimeException) {
                e.printStackTrace()
                getRuntimeExceptionResponse()
            } catch (e: Exception) {
                e.printStackTrace()
                Response().apply { resultCode = SERVER_ERROR }
            }
        }
    }

    private suspend fun doRequestInternal(dto: Any): Response {
        return when (dto) {
            is DrugListSearchRequest -> makeDrugSearchRequest(dto)

            is SingleDrugRequest -> makeSingleDrugRequest(dto)

            else -> Response().apply { resultCode = CLIENT_ERROR }
        }
    }

    private suspend fun makeSingleDrugRequest(dto: SingleDrugRequest): Response =
        SingleDrugResponse(drug = drugService.getDrugDetails(drugId = dto.drugId)).apply {
            resultCode = SUCCESS
        }

    private suspend fun makeDrugSearchRequest(dto: DrugListSearchRequest): Response {
        return drugService.getDrugList(
            searchExpression = dto.searchExpression
        ).apply {
            resultCode = SUCCESS
            Log.i("MyLog", "makeDrugSearchRequest resultCode = $resultCode")
        }
    }

    private suspend fun getHttpExceptionResponse(): Response {
        return Response().apply { resultCode = CLIENT_ERROR }
    }

    private suspend fun getRuntimeExceptionResponse(): Response {
        return Response().apply { resultCode = CLIENT_ERROR }
    }

    fun Context.checkInternetReachability(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork =
            connectivityManager.activeNetwork?.let { connectivityManager.getNetworkCapabilities(it) }
                ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    companion object {
        private const val CLIENT_ERROR = 400
        private const val SERVER_ERROR = 500
        private const val NO_INTERNET_ERROR = -1
        private const val SUCCESS = 200
    }
}