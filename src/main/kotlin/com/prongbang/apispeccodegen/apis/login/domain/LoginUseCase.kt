package com.prongbang.apispeccodegen.apis.login.domain

import com.prongbang.apispeccodegen.apis.login.data.LoginRepository
import com.prongbang.apispeccodegen.apis.login.domain.model.LoginRequest
import com.prongbang.apispeccodegen.apis.login.domain.model.LoginResponse
import com.prongbang.apispeccodegen.core.domain.UseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface LoginUseCase: UseCase<LoginRequest?, LoginResponse>

@Service
class LoginUseCaseImpl : LoginUseCase {

    @Autowired
    lateinit var loginRepository: LoginRepository

    override fun execute(request: LoginRequest?): LoginResponse {
        return loginRepository.login(request)
    }

}