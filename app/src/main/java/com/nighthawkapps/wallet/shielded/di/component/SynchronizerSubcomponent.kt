package com.nighthawkapps.wallet.shielded.di.component

import androidx.lifecycle.ViewModelProvider
import com.nighthawkapps.wallet.shielded.di.annotation.SynchronizerScope
import com.nighthawkapps.wallet.shielded.di.module.SynchronizerModule
import com.nighthawkapps.wallet.shielded.sdk.Initializer
import com.nighthawkapps.wallet.shielded.sdk.Synchronizer
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@SynchronizerScope
@Subcomponent(modules = [SynchronizerModule::class])
interface SynchronizerSubcomponent {

    fun synchronizer(): Synchronizer

    @Named("Synchronizer") fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance initializer: Initializer): SynchronizerSubcomponent
    }
}