package com.berke.internshipproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.berke.internshipproject.base.BaseActionState
import com.berke.internshipproject.base.BaseViewModel
import com.berke.internshipproject.model.ImageModel
import com.berke.internshipproject.model.Result
import com.berke.internshipproject.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val listRepository: ListRepository):
    BaseViewModel<BaseActionState>(ActionState.Init) {

    private val _photos = MutableLiveData<List<ImageModel>>()
    val photos: LiveData<List<ImageModel>>
        get() = _photos

    fun getPhotos(search: String) {
        listRepository.searchImages(search).onEach {
            when(it) {
                is Result.Success -> {
                    setLoading(false)
                    updateActionState(ActionState.ShowingPhotos)
                    updateViewState((currentViewState()).copy(text = "Success"))
                    _photos.value = it.data.hits
                }
                is Result.Error -> {
                    setLoading(false)
                    updateActionState(ActionState.ShowErrorDialog("Title", "Error!"))
                    updateViewState((currentViewState()).copy(text = "Error"))
                }
                is Result.Loading -> {
                    setLoading(true)
                    updateViewState((currentViewState()).copy(text = "Loading"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private val mViewState = MutableStateFlow(ListViewState())
    val viewState: StateFlow<ListViewState>
        get() = mViewState

    private fun currentViewState() = mViewState.value

    private fun updateViewState(viewState: ListViewState) {
        mViewState.value = viewState
    }
}