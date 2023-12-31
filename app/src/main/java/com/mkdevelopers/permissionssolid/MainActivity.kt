package com.mkdevelopers.permissionssolid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mkdevelopers.permissionssolid.databinding.ActivityMainBinding
import com.mkdevelopers.permissionssolid.solid.callpermissions.CallPermissions
import com.mkdevelopers.permissionssolid.solid.camerapermissions.CameraPermissions
import com.mkdevelopers.permissionssolid.solid.permissionhandler.PermissionHandler
import com.mkdevelopers.permissionssolid.solid.permissionhandler.PermissionResult

class MainActivity : AppCompatActivity() {

    private val callPermissionsHandler: PermissionHandler by lazy {
        CallPermissions(
            activity         = this,
            permissionResult = ::permissionResult
        )
    }

    private val cameraPermissionHandler: PermissionHandler by lazy {
        CameraPermissions(
            activity         = this,
            permissionResult = ::permissionResult
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(callPermissionsHandler)
        lifecycle.addObserver(cameraPermissionHandler)

        callPermissionsHandler.onViewCreated()
        cameraPermissionHandler.onViewCreated()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCallPermissions.setOnClickListener {
            callPermissionsHandler.requestPermissions()
        }

        binding.btnCameraPermissions.setOnClickListener {
            cameraPermissionHandler.requestPermissions()
        }
    }

    private fun permissionResult(permissionResult : PermissionResult){
        when(permissionResult){
            is PermissionResult.Error -> println("permissionResult Error ${permissionResult.error}")
            PermissionResult.PermissionGranted -> println("permissionResult Granted..")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(callPermissionsHandler)
        lifecycle.removeObserver(cameraPermissionHandler)
    }
}