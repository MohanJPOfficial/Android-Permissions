package com.mkdevelopers.permissionssolid.permissiondialog

import android.content.Context
import com.mkdevelopers.permissionssolid.R

class MultiplePermissionRationaleDialog(
    context: Context,
    private val rationalePermissionsDescription: String,
    private val btnClicked: () -> Unit
): PermissionDialog(context) {

    override val description: String
        get() = rationalePermissionsDescription

    override val btnText: String
        get() = "Ok"

    override val image: Int
        get() = R.drawable.baseline_mic_24

    override fun buttonClicked() {
        dismiss()
        btnClicked.invoke()
    }
}