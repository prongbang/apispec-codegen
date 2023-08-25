package com.prongbang.apispeccodegen.exceptions

data class Problem(
    private var logRef: String? = null,
    private var message: String? = null
)