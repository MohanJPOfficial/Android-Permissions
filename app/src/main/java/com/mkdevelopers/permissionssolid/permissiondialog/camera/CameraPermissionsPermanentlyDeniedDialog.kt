package com.mkdevelopers.permissionssolid.permissiondialog.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.mkdevelopers.permissionssolid.R
import com.mkdevelopers.permissionssolid.permissiondialog.PermissionDialog

class CameraPermissionsPermanentlyDeniedDialog(
    context: Context
): PermissionDialog(context) {

    override val description: String
        get() = context.getString(R.string.permanently_camera_permissions_deny_description)

    override val btnText: String
        get() = "Go to settings"

    override val image: Int
        get() = R.drawable.baseline_camera_alt_24

    override fun buttonClicked() {
        dismiss()

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }
}