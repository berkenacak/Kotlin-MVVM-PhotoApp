package com.berke.internshipproject.repository

import com.berke.internshipproject.base.BaseRepository
import com.berke.internshipproject.model.Result
import com.berke.internshipproject.network.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ApiService) : BaseRepository() {

    fun searchImages(word: String) = flow {
        emit(Result.Loading)
        emit(getResponse {
            apiService.searchImage(
                q = word
            )
        })
    }
}