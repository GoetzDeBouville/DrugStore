package com.hellcorp.drugstore.core.data

import com.hellcorp.drugstore.core.data.network.NetworkClient
import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.dto.Response
import com.hellcorp.drugstore.core.data.network.request.DrugListSearchRequest
import com.hellcorp.drugstore.core.data.network.request.SingleDrugRequest
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import com.hellcorp.drugstore.domain.models.NetworkErrors
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoRepository
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DrugRepositoryImpl(
    private val networkClient: NetworkClient
) : DrugInfoRepository, DrugSearchRepository {
    override fun getDrugList(expression: String): Flow<Resource<DrugListSearchResponse>> {
        val request = DrugListSearchRequest(searchExpression = expression)
        return handleResponse<DrugListSearchResponse> { networkClient.doRequest(request) }
    }

    override fun getDrugInfo(id: Int): Flow<Resource<SingleDrugResponse>> {
        val request = SingleDrugRequest(drugId = id)
        return handleResponse<SingleDrugResponse> { networkClient.doRequest(request) }
    }

    @Suppress("TooGenericExceptionCaught")
    private inline fun <reified T> handleResponse(
        crossinline functionToHandle: suspend () -> Response
    ): Flow<Resource<T>> = flow {
        try {
            val response = functionToHandle()
            when (response.resultCode) {
                NO_INTERNET_ERROR -> emit(Resource.Error(NetworkErrors.NoInternet))
                SUCCESS -> {
                    val data =
                        response as? T
                            ?: throw ClassCastException("Can't convert to class ${T::class}")
                    emit(Resource.Success(data))
                }

                CLIENT_ERROR -> emit(Resource.Error(NetworkErrors.ClientError))
                SERVER_ERROR -> emit(Resource.Error(NetworkErrors.ServerError))
                else -> emit(Resource.Error(NetworkErrors.UnknownError))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(NetworkErrors.NoInternet))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(NetworkErrors.ClientError))
        } catch (e: RuntimeException) {
            e.printStackTrace()
            emit(Resource.Error(NetworkErrors.ClientError))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(NetworkErrors.ServerError))
        }
    }

    companion object {
        private const val CLIENT_ERROR = 400
        private const val SERVER_ERROR = 500
        private const val NO_INTERNET_ERROR = -1
        private const val SUCCESS = 200
    }
}