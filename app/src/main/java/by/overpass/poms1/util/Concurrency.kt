package by.overpass.poms1.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

private val backgroundExecutor by lazy {
    return@lazy Executors.newSingleThreadExecutor()
}

private val uiHandler by lazy {
    return@lazy Handler(Looper.getMainLooper())
}

fun runInBackground(task: () -> Unit) {
    backgroundExecutor.execute(task)
}

fun runOnUI(task: () -> Unit) {
    uiHandler.post(task)
}