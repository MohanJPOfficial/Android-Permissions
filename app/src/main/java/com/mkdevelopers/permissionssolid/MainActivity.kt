package com.mkdevelopers.permissionssolid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mkdevelopers.permissionssolid.databinding.ActivityMainBinding
import com.mkdevelopers.permissionssolid.solid.AppPermission
import com.mkdevelopers.permissionssolid.solid.MultiplePermissionHandlerImpl
import com.mkdevelopers.permissionssolid.solid.PermissionHandler
import com.mkdevelopers.permissionssolid.solid.PermissionResult

class MainActivity : AppCompatActivity() {

    private val multiplePermissionHandler: PermissionHandler by lazy {
        MultiplePermissionHandlerImpl(
            this,
            ::permissionResult
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(multiplePermissionHandler)
        multiplePermissionHandler.onViewCreated()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPermission.setOnClickListener {

            val permissionsList = listOf(AppPermission.CALL, AppPermission.CAMERA, AppPermission.RECORD_AUDIO)

            multiplePermissionHandler.requestPermissions(
                permissionsList,
                R.string.rationale_multiple_permission_description,
                R.string.permanently_multiple_permissions_deny_description
            )
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
        lifecycle.removeObserver(multiplePermissionHandler)
    }
}