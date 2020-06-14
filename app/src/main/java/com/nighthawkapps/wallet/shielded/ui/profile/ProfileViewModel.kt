package com.nighthawkapps.wallet.shielded.ui.profile

import androidx.lifecycle.ViewModel
import com.nighthawkapps.wallet.shielded.sdk.Synchronizer
import com.nighthawkapps.wallet.shielded.sdk.ext.twig
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var synchronizer: Synchronizer

    suspend fun getAddress(): String = synchronizer.getAddress()

    override fun onCleared() {
        super.onCleared()
        twig("ProfileViewModel cleared!")
    }
}
