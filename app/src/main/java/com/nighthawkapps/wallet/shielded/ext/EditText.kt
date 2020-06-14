package com.nighthawkapps.wallet.shielded.ext

import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText
import android.widget.TextView
import com.nighthawkapps.wallet.shielded.sdk.ext.convertZecToZatoshi
import com.nighthawkapps.wallet.shielded.sdk.ext.safelyConvertToBigDecimal
import com.nighthawkapps.wallet.shielded.sdk.ext.twig

fun EditText.onEditorActionDone(block: (EditText) -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == IME_ACTION_DONE) {
            block(this)
            true
        } else {
            false
        }
    }
}


fun TextView.convertZecToZatoshi(): Long? {
    return try {
        text.toString().safelyConvertToBigDecimal()?.convertZecToZatoshi() ?: null
    } catch (t: Throwable) {
        twig("Failed to convert text to Zatoshi: $text")
        null
    }
}
