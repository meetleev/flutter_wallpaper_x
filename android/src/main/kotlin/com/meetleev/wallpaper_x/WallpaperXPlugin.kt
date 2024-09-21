package com.meetleev.wallpaper_x

import WallpaperXInterface
import android.app.Activity
import android.app.Application
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** WallpaperXPlugin */
class WallpaperXPlugin : Application(), FlutterPlugin, WallpaperXInterface, ActivityAware {
    private var pluginBinding: FlutterPlugin.FlutterPluginBinding? = null

    private var activity: Activity? = null

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        pluginBinding = flutterPluginBinding
        WallpaperXInterface.setUp(flutterPluginBinding.binaryMessenger, this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        pluginBinding = null;
    }

    override fun setLiveWallpaper(filePath: String, callback: (Result<Boolean>) -> Unit) {
        VideoLiveWallpaper.setLiveWallpaper(pluginBinding!!.applicationContext, filePath)
        callback(Result.success(true))
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivity() {
        activity = null
    }
}
