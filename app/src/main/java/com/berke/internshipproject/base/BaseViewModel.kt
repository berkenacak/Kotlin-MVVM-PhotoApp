package com.berke.internshipproject.base

import androidx.lifecycle.ViewModel
import com.berke.internshipproject.utils.EventWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<ActionState: BaseActionState>(
    baseActionState: ActionState
): ViewModel() {

    /**
     * Triggers loading bar in fragment
     *
     */
    private val mLoadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean>
        get() = mLoadingState

    fun setLoading(loading: Boolean) {
        mLoadingState.value = loading
    }


    /**
     * Triggers action states in fragment
     *
     */
    private val mActionState: MutableStateFlow<EventWrapper<ActionState>> = MutableStateFlow(EventWrapper(baseActionState))
    val actionState: StateFlow<EventWrapper<ActionState>>
        get() = mActionState

    fun updateActionState(state: ActionState) {
        mActionState.value = EventWrapper(state)
    }
}