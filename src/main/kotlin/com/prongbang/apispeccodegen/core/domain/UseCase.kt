package com.prongbang.apispeccodegen.core.domain

interface UseCase<P, R> {
    fun execute(params: P): R
}