package com.mkdevelopers.permissionssolid.solid.camerapermissions

import androidx.appcompat.app.AppCompatActivity
import com.mkdevelopers.permissionssolid.permissiondialog.camera.CameraPermissionsPermanentlyDeniedDialog
import com.mkdevelopers.permissionssolid.permissiondialog.camera.CameraPermissionsRationaleDialog
import com.mkdevelopers.permissionssolid.solid.permissionhandler.AppPermission
import com.mkdevelopers.permissionssolid.solid.permissionhandler.MultiplePermissionHandlerImpl
import com.mkdevelopers.permissionssolid.solid.permissionhandler.PermissionResult

class CameraPermissions(
    private val activity: AppCompatActivity,
    permissionResult: (PermissionResult) -> Unit
): MultiplePermissionHandlerImpl(
    activity,
    permissionResult
) {
    override val permissions: List<AppPermission>
        get() = listOf(AppPermission.CAMERA, AppPermission.RECORD_AUDIO)

    override fun showRationaleDialog() {
        CameraPermissionsRationaleDialog(
            context    = activity,
            btnClicked = ::requestPermissions
        ).show()
    }

    override fun showPermanentlyDeniedDialog() {
        CameraPermissionsPermanentlyDeniedDialog(
            context = activity
        ).show()
    }
}