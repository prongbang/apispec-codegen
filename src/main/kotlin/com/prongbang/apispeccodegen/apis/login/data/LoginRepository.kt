package com.prongbang.apispeccodegen.apis.login.data

import com.prongbang.apispeccodegen.apis.login.domain.model.LoginRequest
import com.prongbang.apispeccodegen.apis.login.domain.model.LoginResponse
import org.springframework.stereotype.Repository

interface LoginRepository {
    fun login(request: LoginRequest?): LoginResponse
}

@Repository
class LoginRepositoryImpl : LoginRepository {

    override fun login(request: LoginRequest?): LoginResponse {
        return LoginResponse(token = "JWT")
    }

}