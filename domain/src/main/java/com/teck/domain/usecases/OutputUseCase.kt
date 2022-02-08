package com.teck.domain.usecases

interface OutputUseCase<T> {
    fun getData(): T
}