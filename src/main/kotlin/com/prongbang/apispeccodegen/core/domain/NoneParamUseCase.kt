package com.prongbang.apispeccodegen.core.domain

interface NoneParamUseCase<R> {
    fun execute(): R
}