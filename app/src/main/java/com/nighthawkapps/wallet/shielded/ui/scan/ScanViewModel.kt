package com.nighthawkapps.wallet.shielded.ui.scan

import androidx.lifecycle.ViewModel
import com.nighthawkapps.wallet.shielded.sdk.Synchronizer
import com.nighthawkapps.wallet.shielded.sdk.ext.twig
import javax.inject.Inject

class ScanViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var synchronizer: Synchronizer

    suspend fun isNotValid(address: String) = synchronizer.validateAddress(address).isNotValid

    override fun onCleared() {
        super.onCleared()
        twig("${javaClass.simpleName} cleared!")
    }

}
