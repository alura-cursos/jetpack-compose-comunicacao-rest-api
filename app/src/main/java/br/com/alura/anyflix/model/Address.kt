package br.com.alura.anyflix.model

data class Address(
    val logradouro: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val complemento: String
)