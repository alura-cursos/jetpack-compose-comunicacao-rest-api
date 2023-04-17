package br.com.alura.anyflix.repositories

import android.util.Log
import br.com.alura.anyflix.model.Address
import br.com.alura.anyflix.network.responses.toAddress
import br.com.alura.anyflix.network.restapi.AddressRestApi
import retrofit2.HttpException
import java.net.ConnectException
import javax.inject.Inject

private const val TAG = "AddressRepository"

class AddressRepository @Inject constructor(
    private val restApi: AddressRestApi
) {

    suspend fun findAddress(cep: String): Address? {
        return try {
            restApi.findAddress(cep).toAddress()
        } catch (e: HttpException) {
            Log.e(TAG, "findAddress: ", e)
            null
        } catch (e: ConnectException) {
            Log.e(TAG, "findAddress: ", e)
            null
        }
    }

}