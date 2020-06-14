package com.nighthawkapps.wallet.shielded.di.module

import android.content.Context
import com.nighthawkapps.wallet.shielded.di.annotation.SynchronizerScope
import com.nighthawkapps.wallet.shielded.sdk.Initializer
import com.nighthawkapps.wallet.shielded.sdk.Synchronizer
import dagger.Module
import dagger.Provides

/**
 * Module that creates the synchronizer from an initializer and also everything that depends on the
 * synchronizer (because it doesn't exist prior to this module being installed).
 */
@Module(includes = [ViewModelsSynchronizerModule::class])
class SynchronizerModule {

    @Provides
    @SynchronizerScope
    fun provideSynchronizer(appContext: Context, initializer: Initializer): Synchronizer {
        return Synchronizer(initializer)
    }

}
