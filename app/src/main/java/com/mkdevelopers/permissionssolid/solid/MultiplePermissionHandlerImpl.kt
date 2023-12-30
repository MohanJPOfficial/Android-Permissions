package com.mkdevelopers.permissionssolid.solid

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mkdevelopers.permissionssolid.permissiondialog.MultiplePermissionPermanentDialog
import com.mkdevelopers.permissionssolid.permissiondialog.MultiplePermissionRationaleDialog
import kotlin.properties.Delegates

class MultiplePermissionHandlerImpl(
    private val activity: AppCompatActivity,
    private val permissionResult: (PermissionResult) -> Unit
): PermissionHandler {

    private lateinit var permissionLauncher:  ActivityResultLauncher<Array<String>>
    private lateinit var appPermissions: List<AppPermission>

    @delegate:StringRes
    private var rationalePermissionsDescription by Delegates.notNull<Int>()

    @delegate:StringRes
    private var permanentlyDenyPermissionsDescription by Delegates.notNull<Int>()

    override fun onViewCreated() {
        permissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            ::onPermissionResult
        )
    }

    override fun requestPermissions(
        permissionList: List<AppPermission>,
        rationalePermissionDescription: Int,
        permanentlyDenyPermissionDescription: Int
    ) {
        try {
            appPermissions = permissionList
            this.rationalePermissionsDescription = rationalePermissionDescription
            this.permanentlyDenyPermissionsDescription = permanentlyDenyPermissionDescription

            val deniedPermissions = filterDeniedPermissions(appPermissions)

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

        val permissionKeyList = deniedList.mapNotNull { permission ->
            AppPermission.entries.firstOrNull { it.permissionValue == permission }?.permissionKey
        }

        if(deniedList.any { activity.shouldShowRequestPermissionRationale(it) }) {
            MultiplePermissionRationaleDialog(
                activity,
                activity.getString(rationalePermissionsDescription, permissionKeyList.joinToString())
            ) {
                requestPermissions(
                    appPermissions,
                    rationalePermissionsDescription,
                    permanentlyDenyPermissionsDescription
                )
            }.show()

        } else if(deniedList.isNotEmpty()) {
            MultiplePermissionPermanentDialog(
                activity,
                activity.getString(permanentlyDenyPermissionsDescription, permissionKeyList.joinToString())
            ).show()
        }
    }
}