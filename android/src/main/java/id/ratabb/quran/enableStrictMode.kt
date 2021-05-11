package id.ratabb.quran

import android.os.Build
import android.os.StrictMode
import id.ratabb.quran.ui.MainActivity

internal fun enableStrictMode() {
    val threadPolicy = StrictMode.ThreadPolicy.Builder()
        .detectAll()
    val vmPolicy = StrictMode.VmPolicy.Builder()
        .detectAll()
        .setClassInstanceLimit(MainActivity::class.java, 1) //
    if (Build.VERSION.SDK_INT >= 29) vmPolicy.detectImplicitDirectBoot()
    StrictMode.setThreadPolicy(threadPolicy.penaltyLog().build())
    StrictMode.setVmPolicy(vmPolicy.penaltyLog().build())
}
