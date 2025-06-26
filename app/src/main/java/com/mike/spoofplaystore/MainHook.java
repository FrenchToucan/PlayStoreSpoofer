package com.mike.playstorespoofer;

import android.content.pm.PackageManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    private static final String TAG = "PlayStoreSpoofer";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        // Skip system or null processes
        if (lpparam.packageName == null || lpparam.processName == null) {
            return;
        }

        // Spoof getInstallerPackageName
        XposedHelpers.findAndHookMethod(
                PackageManager.class,
                "getInstallerPackageName",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        String targetPackage = (String) param.args[0];
                        Log.d(TAG, "Spoofing installer package name for: " + targetPackage);
                        param.setResult("com.android.vending"); // Always spoof Play Store as installer
                    }
                }
        );

        // Optionally spoof getPackageInfo if needed
        XposedHelpers.findAndHookMethod(
                PackageManager.class,
                "getPackageInfo",
                String.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String pkg = (String) param.args[0];
                        if ("com.android.vending".equals(pkg)) {
                            Log.d(TAG, "Spoofing presence of Play Store for getPackageInfo");
                            // No override needed here unless you want to return a custom result
                        }
                    }
                }
        );
    }
}
