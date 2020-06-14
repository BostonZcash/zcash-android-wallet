package com.nighthawkapps.wallet.shielded.di.component

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.nighthawkapps.wallet.shielded.di.annotation.ActivityScope
import com.nighthawkapps.wallet.shielded.di.module.MainActivityModule
import com.nighthawkapps.wallet.shielded.ui.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivitySubcomponent {

    fun inject(activity: MainActivity)

    @Named("BeforeSynchronizer") fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity): MainActivitySubcomponent
    }
}