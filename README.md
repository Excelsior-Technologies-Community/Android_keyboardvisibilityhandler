## Keyboard Visibility Handler
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)

A lightweight, lifecycle-aware Android library to easily detect keyboard open/close state with height support.

---

### Features

-  Detect keyboard **OPEN / CLOSE**
-  Get **keyboard height**
-  **Debounced callbacks** (no multiple triggers)
-  **Lifecycle-aware** (no memory leaks)
-  Clean **builder-style API**
-  Lightweight & easy to integrate


---

## Installation

### Step 1: Add JitPack

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add Dependency

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_InternetRetryWidget:1.0.0'
	}
```

---

### Basic Usage

Kotlin Usage
```kotlin
private lateinit var keyboardHandler: KeyboardVisibilityHandler
KeyboardVisibilityHandler.with(this)
    .onOpen { height ->
        Log.d("Keyboard", "Opened height: $height")
    }
    .onClose {
        Log.d("Keyboard", "Closed")
    }
    .bindToLifecycle(this)
    .start()
```

Make sure your activity uses:
```kotlin
android:windowSoftInputMode="adjustResize"
```

Only detect open:
```kotlin
KeyboardVisibilityHandler.with(this)
    .onOpen { height -> }
    .start()
```

Stop manually:
```kotlin
handler.stop()
```

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
