package com.example.gamebuddies.presentation

import android.Manifest
import android.content.ContentProvider
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebuddies.databinding.ActivityVideoCallBinding
import io.agora.rtc2.RtcEngine

class VideoCallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoCallBinding
    private lateinit var rtcEngine: RtcEngine

    private val appId = "a9a1459867454950be9fb8d017ddd902"
    private val token = "007eJxTYFj10jBKrOfCibCcJq4Sn8eblybdZmv3ldn+4u76xtoYjQAFhkTLREMTU0sLM3MTUxNLU4OkVMu0JIsUA0PzlJQUSwMjkT3/0xoCGRncFxcwMEIhiC/MkJ6Ym5pUmpKSmVocn5yRmJeXmsPAAABlkCV/"    // Replace with your temporary token
    private val channelName = "gamebuddies_channel"

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkPermissions()) {
            // Initializing Agora SDK
            initializeAgoraEngine()

            // Join the video call channel
            joinChannel()
        } else {

        }

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkPermissions(): Boolean {
        return checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
    }

    private fun initializeAgoraEngine() {

    }

    private fun joinChannel() {
        rtcEngine.joinChannel(token, channelName, "", 0)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Stop local video preview
        rtcEngine.stopPreview();
        // Leave the channel
        rtcEngine.leaveChannel()
    }
}


/*
 // CORE-SDK
  private static final int PERMISSION_REQ_ID = 22;
  private static final String[] REQUESTED_PERMISSIONS =
  {
      Manifest.permission.RECORD_AUDIO,
      Manifest.permission.CAMERA
  };

  private boolean checkSelfPermission(){
    if (ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED ||
    ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSIONS[1]) != PackageManager.PERMISSION_GRANTED){
    return false;
    } else return true;
  }
* */