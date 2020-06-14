package com.nighthawkapps.wallet.shielded.di.module

import android.content.Context
import com.nighthawkapps.wallet.shielded.sdk.Initializer
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class InitializerModule {
    private val host = "lightwalletd.z.cash"
    private val port = 9067

    @Provides
    @Reusable
    fun provideInitializer(appContext: Context) = Initializer(appContext, host, port)
}
