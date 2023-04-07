package br.com.alura.anyflix.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import br.com.alura.anyflix.ui.uistates.AddressFormUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFormScreen(
    uiState: AddressFormUiState,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit
) {
    val cep = uiState.cep
    val logradouro = uiState.logradouro
    val numero = uiState.numero
    val bairro = uiState.bairro
    val cidade = uiState.cidade
    val complemento = uiState.complemento
    val estado = uiState.estado
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val textFieldModifier = Modifier
            .fillMaxWidth()
        val textFieldColors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF3F3D3D),
            placeholderColor = MaterialTheme.colorScheme.onPrimary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary
        )
        TextField(
            value = cep,
            onValueChange = uiState.onCepChange,
            textFieldModifier,
            label = {
                Text(text = "CEP")
            },
            colors = textFieldColors
        )
        TextField(
            value = logradouro,
            onValueChange = uiState.onLogradouroChange,
            textFieldModifier,
            label = {
                Text(text = "Logradouro")
            },
            colors = textFieldColors
        )
        TextField(
            value = numero,
            onValueChange = uiState.onNumeroChange,
            textFieldModifier,
            label = {
                Text(text = "Número")
            },
            colors = textFieldColors
        )
        TextField(
            value = bairro,
            onValueChange = uiState.onBairroChange,
            textFieldModifier,
            label = {
                Text(text = "Bairro")
            },
            colors = textFieldColors
        )
        TextField(
            value = cidade,
            onValueChange = uiState.onCidadeChange,
            textFieldModifier,
            label = {
                Text(text = "Cidade")
            },
            colors = textFieldColors
        )
        TextField(
            value = estado,
            onValueChange = uiState.onEstadoChange,
            textFieldModifier,
            label = {
                Text(text = "Estado")
            },
            colors = textFieldColors
        )
        TextField(
            value = complemento,
            onValueChange = uiState.onComplementoChange,
            textFieldModifier,
            label = {
                Text(text = "Complemento")
            },
            colors = textFieldColors
        )
        Button(
            onClick = onSaveClick,
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview
@Composable
fun AddressFormScreenPreview() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddressFormScreen(
                uiState = AddressFormUiState(),
                onSaveClick = {}
            )
        }
    }
}