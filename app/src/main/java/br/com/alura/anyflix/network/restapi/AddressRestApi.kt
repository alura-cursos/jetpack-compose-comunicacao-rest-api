package br.com.alura.anyflix.network.restapi

import br.com.alura.anyflix.network.responses.AddressResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://viacep.com.br/ws"

@Singleton
class AddressRestApi @Inject constructor(
    private val client: HttpClient
) {

    suspend fun findAddress(cep: String): AddressResponse {
        return client.get("$BASE_URL/$cep/json").body()
    }

}