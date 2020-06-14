package com.nighthawkapps.wallet.shielded.ext

fun Boolean.asString(ifTrue: String = "", ifFalse: String = "") = if(this) ifTrue else ifFalse