package br.com.alura.anyflix.network.responses

import br.com.alura.anyflix.model.Address
import kotlinx.serialization.Serializable

@Serializable
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