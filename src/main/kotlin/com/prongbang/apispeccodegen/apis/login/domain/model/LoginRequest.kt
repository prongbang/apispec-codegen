package com.prongbang.apispeccodegen.apis.login.domain.model

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @NotBlank
    val username: String,
    @NotBlank
    val password: String,
)