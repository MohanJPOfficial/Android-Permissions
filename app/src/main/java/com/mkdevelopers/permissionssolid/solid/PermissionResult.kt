package com.mkdevelopers.permissionssolid.solid

sealed interface PermissionResult {
    data object PermissionGranted : PermissionResult
    data class Error(val error: String) : PermissionResult
}