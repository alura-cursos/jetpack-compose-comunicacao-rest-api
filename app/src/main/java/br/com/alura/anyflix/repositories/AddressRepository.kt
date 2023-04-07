package br.com.alura.anyflix.repositories

import android.util.Log
import br.com.alura.anyflix.model.Address
import br.com.alura.anyflix.network.services.AddressService
import br.com.alura.anyflix.network.services.toAddress
import retrofit2.HttpException
import java.net.ConnectException
import javax.inject.Inject

private const val TAG = "AddressRepository"

class AddressRepository @Inject constructor(
    private val service: AddressService
) {

    suspend fun findAddress(cep: String): Address? {
        return try {
            service.findAddress(cep).toAddress()
        } catch (e: HttpException) {
            Log.e(TAG, "findAddress: ", e)
            null
        } catch (e: ConnectException) {
            Log.e(TAG, "findAddress: ", e)
            null
        }
    }

}