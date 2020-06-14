package com.nighthawkapps.wallet.shielded.ext

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.nighthawkapps.wallet.shielded.ZcashWalletApp

/**
 * Grab a color out of the application resources, using the default theme
 */
@ColorInt
internal inline fun @receiver:ColorRes Int.toAppColor(): Int {
    return ResourcesCompat.getColor(ZcashWalletApp.instance.resources, this, ZcashWalletApp.instance.theme)
}

/**
 * Grab a string from the application resources
 */
internal inline fun @receiver:StringRes Int.toAppString(): String {
    return ZcashWalletApp.instance.getString(this)}


/**
 * Grab an integer from the application resources
 */
internal inline fun @receiver:IntegerRes Int.toAppInt(): Int {
    return ZcashWalletApp.instance.resources.getInteger(this)}


fun Float.toPx() = this * Resources.getSystem().displayMetrics.density

fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Int.toDp() = (this / Resources.getSystem().displayMetrics.density + 0.5f).toInt()