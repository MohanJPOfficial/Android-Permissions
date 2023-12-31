package com.mkdevelopers.permissionssolid.solid.callpermissions

import androidx.appcompat.app.AppCompatActivity
import com.mkdevelopers.permissionssolid.permissiondialog.call.CallPermissionsPermanentlyDeniedDialog
import com.mkdevelopers.permissionssolid.permissiondialog.call.CallPermissionsRationaleDialog
import com.mkdevelopers.permissionssolid.solid.permissionhandler.AppPermission
import com.mkdevelopers.permissionssolid.solid.permissionhandler.MultiplePermissionHandlerImpl
import com.mkdevelopers.permissionssolid.solid.permissionhandler.PermissionResult

class CallPermissions(
    private val activity: AppCompatActivity,
    permissionResult: (PermissionResult) -> Unit
): MultiplePermissionHandlerImpl(
    activity,
    permissionResult
) {
    override val permissions: List<AppPermission>
        get() = listOf(AppPermission.CALL, AppPermission.RECORD_AUDIO)

    override fun showRationaleDialog() {
        CallPermissionsRationaleDialog(
            context    = activity,
            btnClicked = ::requestPermissions
        ).show()
    }

    override fun showPermanentlyDeniedDialog() {
        CallPermissionsPermanentlyDeniedDialog(
            context = activity
        ).show()
    }
}