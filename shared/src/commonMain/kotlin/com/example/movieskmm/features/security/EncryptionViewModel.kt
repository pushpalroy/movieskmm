package com.example.movieskmm.features.security

import com.example.movieskmm.BuildKonfig
import com.example.movieskmm.domain.usecase.security.CheckEncryptionPassphraseUseCase
import com.example.movieskmm.domain.usecase.security.CloseDatabaseUseCase
import com.example.movieskmm.domain.usecase.security.EnableEncryptionUseCase
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class EncryptionViewModel : KMMViewModel(), KoinComponent {

    private val enableEncryptionUseCase: EnableEncryptionUseCase by inject()
    private val checkEncryptionUseCase: CheckEncryptionPassphraseUseCase by inject()
    private val closeDatabaseUseCase: CloseDatabaseUseCase by inject()

    fun enableOrCheckEncryption() {
        viewModelScope.coroutineScope.launch {
            enableEncryptionUseCase.perform(BuildKonfig.DB_ENCRYPTION_PASS)
            checkEncryptionUseCase.perform(BuildKonfig.DB_ENCRYPTION_PASS)
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.launch {
            closeDatabaseUseCase.perform()
        }
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}