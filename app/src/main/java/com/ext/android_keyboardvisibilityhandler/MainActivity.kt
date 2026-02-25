package com.ext.android_keyboardvisibilityhandler

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.keyboardvisibilityhandler.KeyboardListener
import com.ext.keyboardvisibilityhandler.KeyboardState
import com.ext.keyboardvisibilityhandler.KeyboardVisibilityHandler

class MainActivity : AppCompatActivity() {
    private lateinit var keyboardHandler: KeyboardVisibilityHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        keyboardHandler = KeyboardVisibilityHandler(this)

        keyboardHandler.setKeyboardListener(object : KeyboardListener {
            override fun onKeyboardStateChanged(state: KeyboardState) {
                when (state) {
                    KeyboardState.OPEN -> {
                        Log.d("KeyboardTest", "Keyboard Opened ✅")
                    }
                    KeyboardState.CLOSED -> {
                        Log.d("KeyboardTest", "Keyboard Closed ❌")
                    }
                }
            }
        })
    }
}