package com.berke.internshipproject.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.berke.internshipproject.base.BaseActionState
import com.berke.internshipproject.base.BaseViewModel
import com.berke.internshipproject.model.Favorites
import com.berke.internshipproject.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val detailRepository: DetailRepository):
    BaseViewModel<BaseActionState>(FavoritesActionState.Init) {

    val allImages: MutableLiveData<List<Favorites>> = MutableLiveData()

    fun getFavoritePhotos() {
        viewModelScope.launch {
            allImages.value = detailRepository.allImages()
            updateActionState(FavoritesActionState.ShowMessage("All favorites photos are showing.. Size: ${allImages.value?.size ?: 0}"))
        }
    }
}