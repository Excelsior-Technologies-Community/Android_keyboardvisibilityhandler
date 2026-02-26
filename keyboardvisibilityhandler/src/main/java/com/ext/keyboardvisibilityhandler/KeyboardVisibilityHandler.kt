package com.ext.keyboardvisibilityhandler

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class KeyboardVisibilityHandler private constructor(
    private val activity: Activity
) : DefaultLifecycleObserver {

    private var onOpen: ((height: Int) -> Unit)? = null
    private var onClose: (() -> Unit)? = null

    private var isKeyboardVisible = false
    private var lastTriggerTime = 0L

    private var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    companion object {
        fun with(activity: Activity): KeyboardVisibilityHandler {
            return KeyboardVisibilityHandler(activity)
        }
    }

    fun onOpen(action: (height: Int) -> Unit) = apply {
        this.onOpen = action
    }

    fun onClose(action: () -> Unit) = apply {
        this.onClose = action
    }

    fun start() {
        val rootView = activity.findViewById<View>(android.R.id.content)

        globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)

            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            val isOpen = keypadHeight > screenHeight * 0.15

            val currentTime = System.currentTimeMillis()

            // 🔥 Debounce (300ms)
            if (currentTime - lastTriggerTime < 300) return@OnGlobalLayoutListener

            if (isOpen != isKeyboardVisible) {
                isKeyboardVisible = isOpen
                lastTriggerTime = currentTime

                if (isOpen) {
                    onOpen?.invoke(keypadHeight)
                } else {
                    onClose?.invoke()
                }
            }
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    fun stop() {
        val rootView = activity.findViewById<View>(android.R.id.content)
        globalLayoutListener?.let {
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(it)
        }
        globalLayoutListener = null
    }

    // 🔥 Lifecycle Safe
    fun bindToLifecycle(owner: LifecycleOwner) = apply {
        owner.lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        stop()
    }
}