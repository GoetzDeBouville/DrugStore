package com.hellcorp.drugstore.core.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.hellcorp.drugstore.core.data.network.dto.Response
import com.hellcorp.drugstore.core.data.network.request.DrugListSearchRequest
import com.hellcorp.drugstore.core.data.network.request.SingleDrugRequest
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import com.hellcorp.drugstore.utils.Constants.Companion.CLIENT_ERROR
import com.hellcorp.drugstore.utils.Constants.Companion.NO_INTERNET_ERROR
import com.hellcorp.drugstore.utils.Constants.Companion.SERVER_ERROR
import com.hellcorp.drugstore.utils.Constants.Companion.SUCCESS
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
                Log.i("MyLog", "doRequestInternal")
                doRequestInternal(dto)
            } catch (e: IOException) {
                Log.i("MyLog", "IOException")
                e.printStackTrace()
                Response().apply { resultCode = NO_INTERNET_ERROR }
            } catch (e: HttpException) {
                Log.i("MyLog", "HttpException")
                e.printStackTrace()
                getHttpExceptionResponse()
            } catch (e: RuntimeException) {
                e.printStackTrace()
                Log.i("MyLog", "RuntimeException")
                getRuntimeExceptionResponse()
            } catch (e: Exception) {
                Log.i("MyLog", "Exception")
                e.printStackTrace()
                Response().apply { resultCode = SERVER_ERROR }
            }
        }
    }

    private suspend fun doRequestInternal(dto: Any): Response {
        return when (dto) {
            is DrugListSearchRequest -> {
                Log.i("MyLog", "DrugListSearchRequest")
                makeDrugSearchRequest(dto)
            }

            is SingleDrugRequest -> {
                Log.i("MyLog", "SingleDrugRequest")
                makeSingleDrugRequest(dto)
            }

            else -> {
                Log.i("MyLog", "Response().apply CLIENT_ERROR")
                Response().apply { resultCode = CLIENT_ERROR }
            }
        }
    }

    private suspend fun makeSingleDrugRequest(dto: SingleDrugRequest): Response =
        SingleDrugResponse(drug = drugService.getDrugDetails(drugId = dto.drugId)).apply {
            resultCode = SUCCESS
        }

    private suspend fun makeDrugSearchRequest(dto: DrugListSearchRequest): Response {
        Log.i("MyLog", "Making drug search request: $dto")
        val response = drugService.getDrugList(searchExpression = dto.searchExpression)
        Log.i("MyLog", "Received response: $response")
        return response.apply {
            resultCode = SUCCESS
        }
    }

    private suspend fun getHttpExceptionResponse(): Response {
        Log.i("MyLog", "getHttpExceptionResponse CLIENT_ERROR")
        return Response().apply { resultCode = CLIENT_ERROR }
    }

    private suspend fun getRuntimeExceptionResponse(): Response {
        Log.i("MyLog", "getRuntimeExceptionResponse CLIENT_ERROR")
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
}