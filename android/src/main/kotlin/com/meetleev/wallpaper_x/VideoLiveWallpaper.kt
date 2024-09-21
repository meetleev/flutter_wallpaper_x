package com.meetleev.wallpaper_x

import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Build
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import java.io.IOException

private fun sharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("VideoLiveWallpaper", MODE_PRIVATE)
}

class VideoLiveWallpaper : WallpaperService() {
    companion object {
        const val VIDEO_FILE_PATH = "VIDEO_FILE_PATH"
        const val VIDEO_PARAMS_CONTROL_ACTION: String = "com.meetleev.wallpaper_x"
        const val KEY_MUSIC_ACTION: String = "music"
        fun setLiveWallpaper(context: Context, videFilePath: String) {
            val editor = sharedPreferences(context).edit()
            editor.putString(VIDEO_FILE_PATH, videFilePath)
            editor.apply()
            val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(
                    context, VideoLiveWallpaper::class.java
                )
            )
            context.startActivity(intent)
            try {
                WallpaperManager.getInstance(context).clear()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    override fun onCreateEngine(): Engine {
        return VideoEngine()
    }

    inner class VideoEngine : Engine() {
        private var mediaPlayer: MediaPlayer? = null
        private var broadcastReceiver: BroadcastReceiver? = null

        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        override fun onCreate(surfaceHolder: SurfaceHolder) {
            super.onCreate(surfaceHolder)
            val intentFilter = IntentFilter(VIDEO_PARAMS_CONTROL_ACTION)
            registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val action = intent.getBooleanExtra(KEY_MUSIC_ACTION, false)
                    if (action) {
                        mediaPlayer!!.setVolume(0f, 0f)
                    } else {
                        mediaPlayer!!.setVolume(1.0f, 1.0f)
                    }
                }
            }.also { broadcastReceiver = it }, intentFilter, RECEIVER_NOT_EXPORTED)
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)
            initializeMediaPlayer(holder)
        }

        private fun initializeMediaPlayer(holder: SurfaceHolder) {
            try {
                val videFilePath =
                    sharedPreferences(applicationContext).getString(VIDEO_FILE_PATH, "")
                if (videFilePath.isNullOrEmpty()) Log.e(
                    "VideoLiveWallpaper",
                    "initializeMediaPlayer null"
                )
                else Log.d("VideoLiveWallpaper", videFilePath)
                if (mediaPlayer == null && !videFilePath.isNullOrEmpty()) {
                    mediaPlayer = MediaPlayer()
                    mediaPlayer!!.setSurface(holder.surface)
                    mediaPlayer!!.setDataSource(videFilePath)
                    mediaPlayer!!.isLooping = true
                    mediaPlayer!!.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    mediaPlayer!!.setVolume(0f, 0f)
                    Log.d("VideoEngine", "MediaPlayer started successfully")
                }
            } catch (e: IOException) {
                Log.e("VideoEngine", "Error initializing MediaPlayer", e)
            }
        }

        override fun onVisibilityChanged(visible: Boolean) {
            if (mediaPlayer != null) {
                if (visible) {
                    mediaPlayer!!.start()
                } else {
                    mediaPlayer!!.pause()
                }
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) mediaPlayer!!.stop()
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
                mediaPlayer = null
            }
            unregisterReceiver(broadcastReceiver)
        }
    }
}