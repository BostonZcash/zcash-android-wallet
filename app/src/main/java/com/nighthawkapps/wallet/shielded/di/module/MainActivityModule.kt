package com.nighthawkapps.wallet.shielded.di.module

import com.nighthawkapps.wallet.shielded.di.component.InitializerSubcomponent
import com.nighthawkapps.wallet.shielded.di.component.SynchronizerSubcomponent
import dagger.Module

@Module(includes = [ViewModelsActivityModule::class], subcomponents = [SynchronizerSubcomponent::class, InitializerSubcomponent::class])
class MainActivityModule {

}
