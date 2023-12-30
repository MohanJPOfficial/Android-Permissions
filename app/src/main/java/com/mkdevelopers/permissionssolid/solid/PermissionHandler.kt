package com.mkdevelopers.permissionssolid.solid

import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver

interface PermissionHandler: DefaultLifecycleObserver {

    fun onViewCreated()

    fun requestPermissions(
        permissionList: List<AppPermission>,

        @StringRes
        rationalePermissionDescription: Int,

        @StringRes
        permanentlyDenyPermissionDescription: Int
    )
}