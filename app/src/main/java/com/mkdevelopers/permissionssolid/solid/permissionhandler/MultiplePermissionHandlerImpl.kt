package com.mkdevelopers.permissionssolid.solid.permissionhandler

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class MultiplePermissionHandlerImpl(
    private val activity: AppCompatActivity,
    private val permissionResult: (PermissionResult) -> Unit
): PermissionHandler {

    private lateinit var permissionLauncher:  ActivityResultLauncher<Array<String>>

    override fun onViewCreated() {
        permissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            ::onPermissionResult
        )
    }

    override fun requestPermissions() {
        try {

            val deniedPermissions = filterDeniedPermissions(permissions)

            if(deniedPermissions.isEmpty())
                permissionResult.invoke(PermissionResult.PermissionGranted)
            else
                permissionLauncher.launch(deniedPermissions.map { it.permissionValue }.toTypedArray())

        } catch (e : Exception){
            permissionResult.invoke(PermissionResult.Error(e.toString()))
        }
    }

    private fun filterDeniedPermissions(appPermissions: List<AppPermission>): List<AppPermission> {
        return appPermissions.filter { appPermission ->
            ActivityCompat.checkSelfPermission(activity, appPermission.permissionValue) != PackageManager.PERMISSION_GRANTED
        }
    }

    private fun onPermissionResult(permissionsMap: Map<String, Boolean>) {

        val deniedList: List<String> = permissionsMap.filter { !it.value }.map { it.key }

        when {
            deniedList.any { activity.shouldShowRequestPermissionRationale(it) } -> showRationaleDialog()

            deniedList.isNotEmpty() -> showPermanentlyDeniedDialog()

            else -> permissionResult.invoke(PermissionResult.PermissionGranted)
        }
    }

    abstract val permissions: List<AppPermission>

    abstract fun showRationaleDialog()

    abstract fun showPermanentlyDeniedDialog()
}