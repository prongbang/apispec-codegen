package com.prongbang.apispeccodegen.apis.login.controller

import com.prongbang.apispeccodegen.apis.login.domain.model.LoginRequest
import com.prongbang.apispeccodegen.apis.login.domain.LoginUseCase
import com.prongbang.apispeccodegen.apis.login.domain.model.LoginResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @Autowired
    lateinit var loginUseCase: LoginUseCase

    @PostMapping("/login")
    fun person(@RequestBody loginRequest: @Valid LoginRequest?): LoginResponse? {
        return loginUseCase.execute(loginRequest)
    }

}