package com.berke.internshipproject.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error<out T>(val message: String) : Result<T>()
}
