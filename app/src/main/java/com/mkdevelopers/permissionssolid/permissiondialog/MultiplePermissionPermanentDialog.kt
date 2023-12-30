package com.mkdevelopers.permissionssolid.permissiondialog

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.mkdevelopers.permissionssolid.R

class MultiplePermissionPermanentDialog(
    context: Context,
    private val permanentlyPermissionDenyDescription: String
): PermissionDialog(context) {

    override val description: String
        get() = permanentlyPermissionDenyDescription

    override val btnText: String
        get() = "Go to settings"

    override val image: Int
        get() = R.drawable.baseline_mic_24

    override fun buttonClicked() {
        dismiss()

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }
}