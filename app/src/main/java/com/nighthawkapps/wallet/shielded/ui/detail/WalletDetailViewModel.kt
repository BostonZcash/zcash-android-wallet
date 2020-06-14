package com.nighthawkapps.wallet.shielded.ui.detail

import androidx.lifecycle.ViewModel
import com.nighthawkapps.wallet.shielded.sdk.Synchronizer
import com.nighthawkapps.wallet.shielded.sdk.ext.twig
import javax.inject.Inject

class WalletDetailViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var synchronizer: Synchronizer

    val transactions get() = synchronizer.clearedTransactions
    val balance get() = synchronizer.balances

    suspend fun getAddress() = synchronizer.getAddress()

    override fun onCleared() {
        super.onCleared()
        twig("WalletDetailViewModel cleared!")
    }
}
