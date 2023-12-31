package com.mkdevelopers.permissionssolid.permissiondialog.call

import android.content.Context
import com.mkdevelopers.permissionssolid.R
import com.mkdevelopers.permissionssolid.permissiondialog.PermissionDialog

class CallPermissionsRationaleDialog(
    context: Context,
    private val btnClicked: () -> Unit
): PermissionDialog(context) {

    override val description: String
        get() = context.getString(R.string.rationale_call_permissions_description)

    override val btnText: String
        get() = "Ok"

    override val image: Int
        get() = R.drawable.baseline_add_call_24

    override fun buttonClicked() {
        dismiss()
        btnClicked.invoke()
    }
}