package com.berke.internshipproject.ui.detail

import com.berke.internshipproject.base.BaseActionState

sealed class DetailActionState: BaseActionState() {
    object Init: DetailActionState()
    data class ShowToast(var message: String): DetailActionState()
}