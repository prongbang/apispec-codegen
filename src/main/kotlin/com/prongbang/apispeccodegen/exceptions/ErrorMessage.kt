package com.prongbang.apispeccodegen.exceptions

import java.util.*

class ErrorMessage {
    private var errors: List<String>? = null

    constructor()

    constructor(errors: List<String>?) {
        this.errors = errors
    }

    constructor(error: String) : this(listOf<String>(error))

    constructor(vararg errors: String?) : this(Arrays.asList<String>(*errors))
}