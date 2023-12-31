package com.mkdevelopers.permissionssolid.solid.permissionhandler

import android.Manifest

enum class AppPermission(
    val permissionValue: String
) {
    CAMERA(Manifest.permission.CAMERA),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO),
    CALL(Manifest.permission.CALL_PHONE)
}