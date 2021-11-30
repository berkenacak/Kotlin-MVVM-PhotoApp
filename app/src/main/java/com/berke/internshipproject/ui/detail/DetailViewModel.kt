package com.berke.internshipproject.ui.detail

import androidx.lifecycle.viewModelScope
import com.berke.internshipproject.base.BaseViewModel
import com.berke.internshipproject.model.Favorites
import com.berke.internshipproject.repository.DetailRepository
import com.berke.internshipproject.base.BaseActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository):
    BaseViewModel<BaseActionState>(DetailActionState.Init) {

    var imageURL: String? = null

    fun insertImage(favorites: Favorites) = viewModelScope.launch {
        updateActionState(DetailActionState.ShowToast("Insert Image Toast"))
        updateViewState((currentViewState()).copy(testText = "Insert Image Text"))
        detailRepository.insertImage(favorites)
    }

    fun deleteImage(url: String) = viewModelScope.launch {
        updateActionState(DetailActionState.ShowToast("Delete Image Toast"))
        updateViewState((currentViewState()).copy(testText = "Delete Image Text"))
        detailRepository.deleteImage(url)
    }

    fun hasContains(url: String) : Boolean {
        var list : List<Favorites> = mutableListOf()
        viewModelScope.launch {
            list = detailRepository.hasContains(url)
        }
        return list.isNotEmpty()
    }


    private val mViewState = MutableStateFlow(DetailViewState())
    val viewState: StateFlow<DetailViewState>
        get() = mViewState

    private fun currentViewState() = mViewState.value

    private fun updateViewState(viewState: DetailViewState) {
        mViewState.value = viewState
    }
}