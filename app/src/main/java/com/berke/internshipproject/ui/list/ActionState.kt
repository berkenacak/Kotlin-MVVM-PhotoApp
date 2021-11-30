package com.berke.internshipproject.ui.list

import com.berke.internshipproject.base.BaseActionState

sealed class ActionState: BaseActionState() {
    object Init: ActionState()
    data class ShowErrorDialog(var title: String? = null, var message: String): ActionState()
    object ShowingPhotos: ActionState()
}