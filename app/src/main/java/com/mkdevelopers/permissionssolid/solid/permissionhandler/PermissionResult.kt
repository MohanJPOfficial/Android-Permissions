package com.mkdevelopers.permissionssolid.solid.permissionhandler

sealed interface PermissionResult {
    data object PermissionGranted : PermissionResult
    data class Error(val error: String) : PermissionResult
}