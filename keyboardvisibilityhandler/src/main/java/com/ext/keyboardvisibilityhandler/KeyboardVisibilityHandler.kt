package com.ext.keyboardvisibilityhandler

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

class KeyboardVisibilityHandler(
    private val activity: Activity
) {

    private var listener: KeyboardListener? = null
    private var isKeyboardVisible = false

    fun setKeyboardListener(listener: KeyboardListener) {
        this.listener = listener
        startListening()
    }

    private fun startListening() {
        val rootView = activity.findViewById<View>(android.R.id.content)

        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)

            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            val isOpen = keypadHeight > screenHeight * 0.15

            if (isOpen != isKeyboardVisible) {
                isKeyboardVisible = isOpen

                if (isOpen) {
                    listener?.onKeyboardStateChanged(KeyboardState.OPEN)
                } else {
                    listener?.onKeyboardStateChanged(KeyboardState.CLOSED)
                }
            }
        }
    }
}