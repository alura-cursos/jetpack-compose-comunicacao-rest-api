package br.com.alura.anyflix.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.anyflix.ui.screens.AddressFormScreen
import br.com.alura.anyflix.ui.viewmodels.AddressFormViewModel

const val addressFormRoute: String = "addressForm"

fun NavGraphBuilder.addressFormScreen() {
    composable(addressFormRoute) {
        val viewModel = hiltViewModel<AddressFormViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        AddressFormScreen(
            uiState = uiState,
            onSaveClick = {
                viewModel.save()
            }
        )
    }
}