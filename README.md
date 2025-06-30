# PlayStoreSpoofer

**PlayStoreSpoofer** is an [LSPosed](https://github.com/LSPosed/LSPosed) module that spoofs the presence of the Google Play Store to other apps and specfically satisfies Pokemon Go's Play Store requirement. It tricks checks like `getInstallerPackageName()` and `getPackageInfo()` into believing the Play Store is installed and was used to install the current app. This module does not impact Play Integrity at all, and is more like a companion where Play Store presence is required on top of Play Integrity.

---

## 🧩 Features

- **Spoofs installer source** by overriding `getInstallerPackageName()` to always return `com.android.vending`
- **Fakes Play Store presence** by intercepting `getPackageInfo("com.android.vending", ...)`
- Minimal and self-contained implementation

---

## 🔧 How It Works

Hooks into the Android `PackageManager` system APIs using LSPosed/Xposed and returns spoofed values that suggest the Play Store is installed and active.

### Hooked Methods:

- `PackageManager.getInstallerPackageName(String packageName)`
- `PackageManager.getPackageInfo(String packageName, int flags)`

---

## 📦 Installation

### Requirements:
- [LSPosed](https://github.com/JingMatrix/LSPosed) or another Xposed-compatible framework
- Rooted Android device
- Android 7.0+ recommended

### Steps:
1. Clone or download this repo
2. Build the APK in Android Studio (`Build > Build APK(s)`)
3. Install the APK on your device
4. Enable the module in the LSPosed app
5. **Select the apps you want to spoof in LSPosed's per-app module settings**
6. Reboot your device

---

## 🛠️ Development

### Project structure:

- `MainHook.java` — the main entry point for the module logic
- `assets/xposed_init` — declares the hook class to LSPosed
- `AndroidManifest.xml` — includes required metadata

### Building:
Open in Android Studio and run a build. Make sure your `xposed_init` file and manifest are correctly set.
