package com.mkdevelopers.permissionssolid.permissiondialog.camera

import android.content.Context
import com.mkdevelopers.permissionssolid.R
import com.mkdevelopers.permissionssolid.permissiondialog.PermissionDialog

class CameraPermissionsRationaleDialog(
    context: Context,
    private val btnClicked: () -> Unit
): PermissionDialog(context) {

    override val description: String
        get() = context.getString(R.string.rationale_camera_permissions_description)

    override val btnText: String
        get() = "Ok"

    override val image: Int
        get() = R.drawable.baseline_camera_alt_24

    override fun buttonClicked() {
        dismiss()
        btnClicked.invoke()
    }
}