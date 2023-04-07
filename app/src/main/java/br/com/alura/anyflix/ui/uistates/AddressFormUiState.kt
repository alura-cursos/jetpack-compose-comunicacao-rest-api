package br.com.alura.anyflix.ui.uistates

data class AddressFormUiState(
    val logradouro: String = "",
    val numero: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val complemento: String = "",
    val estado: String = "",
    val cep: String = "",
    val onLogradouroChange: (String) -> Unit = {},
    val onNumeroChange: (String) -> Unit = {},
    val onBairroChange: (String) -> Unit = {},
    val onCidadeChange: (String) -> Unit = {},
    val onComplementoChange: (String) -> Unit = {},
    val onEstadoChange: (String) -> Unit = {},
    val onCepChange: (String) -> Unit = {}
)
