package com.nighthawkapps.wallet.shielded.di.component

import com.nighthawkapps.wallet.shielded.ZcashWalletApp
import com.nighthawkapps.wallet.shielded.di.annotation.ActivityScope
import com.nighthawkapps.wallet.shielded.di.annotation.SynchronizerScope
import com.nighthawkapps.wallet.shielded.di.module.InitializerModule
import com.nighthawkapps.wallet.shielded.sdk.Initializer
import dagger.BindsInstance
import dagger.Subcomponent

@SynchronizerScope
@Subcomponent(modules = [InitializerModule::class])
interface InitializerSubcomponent {

    fun initializer(): Initializer
    fun birthdayStore(): Initializer.WalletBirthdayStore

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance birthdayStore: Initializer.WalletBirthdayStore = Initializer.DefaultBirthdayStore(ZcashWalletApp.instance)): InitializerSubcomponent
    }
}