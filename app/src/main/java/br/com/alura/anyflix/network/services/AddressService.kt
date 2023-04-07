package br.com.alura.anyflix.network.services

import br.com.alura.anyflix.model.Address
import retrofit2.http.GET
import retrofit2.http.Path

data class AddressResponse(
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val cep: String
)

fun AddressResponse.toAddress() = Address(
    logradouro = logradouro,
    bairro = bairro,
    cidade = localidade,
    estado = uf,
    cep = cep,
    numero = "",
    complemento = ""
)

interface AddressService {

    @GET("{cep}/json")
    suspend fun findAddress(
        @Path("cep") cep: String
    ): AddressResponse

}
