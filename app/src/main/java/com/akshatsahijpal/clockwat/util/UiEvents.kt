package com.akshatsahijpal.clockwat.util

sealed class UiEvents
data class Navigate(var path: String) : UiEvents()
object PopBackStack : UiEvents()
data class ShowSnackBar(
    var message: String? = null,
    var action: String? = null
) : UiEvents()