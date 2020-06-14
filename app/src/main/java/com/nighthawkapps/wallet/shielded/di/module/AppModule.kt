package com.nighthawkapps.wallet.shielded.di.module

import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import com.nighthawkapps.wallet.shielded.ZcashWalletApp
import com.nighthawkapps.wallet.shielded.di.component.MainActivitySubcomponent
import com.nighthawkapps.wallet.shielded.feedback.*
import com.nighthawkapps.wallet.shielded.sdk.ext.SilentTwig
import com.nighthawkapps.wallet.shielded.sdk.ext.TroubleshootingTwig
import com.nighthawkapps.wallet.shielded.sdk.ext.Twig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module(subcomponents = [MainActivitySubcomponent::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(): Context = ZcashWalletApp.instance

    @Provides
    @Singleton
    fun provideClipboard(context: Context) =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


    //
    // Feedback
    //

    @Provides
    @Singleton
    fun providePreferences(context: Context): SharedPreferences
            = context.getSharedPreferences("Application", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideFeedback(): Feedback = Feedback()

    @Provides
    @Singleton
    fun provideFeedbackCoordinator(
        feedback: Feedback,
        preferences: SharedPreferences,
        defaultObservers: Set<@JvmSuppressWildcards FeedbackCoordinator.FeedbackObserver>
    ): FeedbackCoordinator {
        return preferences.getBoolean(FeedbackCoordinator.ENABLED, false).let { isEnabled ->
            // observe nothing unless feedback is enabled
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(isEnabled)
            Twig.plant(if (isEnabled) TroubleshootingTwig() else SilentTwig())
            FeedbackCoordinator(feedback, if (isEnabled) defaultObservers else setOf())
        }
    }


    //
    // Default Feedback Observer Set
    //

    @Provides
    @Singleton
    @IntoSet
    fun provideFeedbackFile(): FeedbackCoordinator.FeedbackObserver = FeedbackFile()

    @Provides
    @Singleton
    @IntoSet
    fun provideFeedbackConsole(): FeedbackCoordinator.FeedbackObserver = FeedbackConsole()

    @Provides
    @Singleton
    @IntoSet
    fun provideFeedbackMixpanel(): FeedbackCoordinator.FeedbackObserver = FeedbackMixpanel()

    @Provides
    @Singleton
    @IntoSet
    fun provideFeedbackCrashlytics(): FeedbackCoordinator.FeedbackObserver = FeedbackCrashlytics()
}
