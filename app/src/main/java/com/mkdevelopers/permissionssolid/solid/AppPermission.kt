package com.mkdevelopers.permissionssolid.solid

import android.Manifest

enum class AppPermission(
    val permissionKey: String,
    val permissionValue: String
) {
    CAMERA("Camera", Manifest.permission.CAMERA),
    RECORD_AUDIO("Record Audio", Manifest.permission.RECORD_AUDIO),
    CALL("Call", Manifest.permission.CALL_PHONE)
}