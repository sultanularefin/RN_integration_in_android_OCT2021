package com.arefin.androidrootnavigation2

import com.facebook.react.common.LifecycleState

import com.facebook.react.ReactInstanceManager

import com.facebook.react.ReactPackage

import com.facebook.react.ReactRootView

import com.facebook.soloader.SoLoader

import android.os.Bundle

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler

import android.app.Activity
import android.view.KeyEvent
import androidx.viewbinding.BuildConfig
import com.facebook.react.PackageList;



//0000

import com.facebook.flipper.*;
//import com.facebook.flipper.android.utils.FlipperUtils;
//import com.facebook.flipper.core.FlipperClient;
//import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin;
//import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin;
//import com.facebook.flipper.plugins.fresco.FrescoFlipperPlugin;
//import com.facebook.flipper.plugins.inspector.DescriptorMapping;
//import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
//import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor;
//import com.facebook.flipper.plugins.network.NetworkFlipperPlugin;
//import com.facebook.flipper.plugins.react.ReactFlipperPlugin;
//import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin;

//0000

class MyReactActivity : Activity(), DefaultHardwareBackBtnHandler {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)
        mReactRootView = ReactRootView(this)
        val packages: List<ReactPackage> = PackageList(application).getPackages()
        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
        mReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        mReactRootView!!.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null)
        setContentView(mReactRootView)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }


//    begining....

    override fun onPause() {
        super.onPause()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostPause(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostResume(this, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostDestroy(this)
        }
        if (mReactRootView != null) {
            mReactRootView!!.unmountReactApplication()
        }
    }
    // end....


    //again....111


    override fun onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    //again....222
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager!!.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    //again....222  ENDS----
}