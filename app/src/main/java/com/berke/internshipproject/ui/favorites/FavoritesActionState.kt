package com.berke.internshipproject.ui.favorites

import com.berke.internshipproject.base.BaseActionState

sealed class FavoritesActionState: BaseActionState() {
    object Init: FavoritesActionState()
    data class ShowMessage(var message: String): FavoritesActionState()
}