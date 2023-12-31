package com.mkdevelopers.permissionssolid.solid.permissionhandler

import androidx.lifecycle.DefaultLifecycleObserver

interface PermissionHandler: DefaultLifecycleObserver {

    fun onViewCreated()

    fun requestPermissions()
}