package br.com.alura.anyflix.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.anyflix.model.Address
import br.com.alura.anyflix.repositories.AddressRepository
import br.com.alura.anyflix.ui.uistates.AddressFormUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddressFormViewModel"

@HiltViewModel
class AddressFormViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressFormUiState())
    val uiState = _uiState.asStateFlow()
    private var job: Job = Job()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onBairroChange = {
                    _uiState.value = _uiState.value.copy(
                        bairro = it
                    )
                },
                onEstadoChange = {
                    _uiState.value = _uiState.value.copy(
                        estado = it
                    )
                },
                onCepChange = {
                    _uiState.value = _uiState.value.copy(
                        cep = it
                    )
                    searchAddress(it)
                },
                onComplementoChange = {
                    _uiState.value = _uiState.value.copy(
                        complemento = it
                    )
                },
                onNumeroChange = {
                    _uiState.value = _uiState.value.copy(
                        numero = it
                    )
                },
                onCidadeChange = {
                    _uiState.value = _uiState.value.copy(
                        cidade = it
                    )
                },
                onLogradouroChange = {
                    _uiState.value = _uiState.value.copy(
                        logradouro = it
                    )
                },
            )
        }
    }

    private fun searchAddress(cep: String) {
        job.cancel()
        job = viewModelScope.launch {
            delay(2000)
            repository.findAddress(cep)?.let { address ->
                _uiState.update {
                    it.copy(
                        logradouro = address.logradouro,
                        bairro = address.bairro,
                        cidade = address.cidade,
                        estado = address.estado,
                    )
                }
            }
        }
    }

    fun save() {
        with(uiState.value) {
            val address = Address(
                logradouro = logradouro,
                numero = numero,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                cep = cep,
                complemento = complemento
            )
            Log.i(TAG, "save: $address")
        }
    }

}