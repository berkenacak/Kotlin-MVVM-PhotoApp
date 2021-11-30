package com.berke.internshipproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.berke.internshipproject.BR
import com.berke.internshipproject.utils.ProgressBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<out ViewModel : BaseViewModel<BaseActionState>, Binding : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    viewModelClass: Class<ViewModel>
) : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    private val progressBar: ProgressBar by lazy {
        ProgressBar(requireContext())
    }

    protected val viewModel: ViewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        _binding?.run {
            setVariable(BR.data, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerLoading()
        init()

        with(viewModel) {
            actionState
                .map { it.getContentIfNotHandled() }
                .onEach(::renderActionState)
                .launchIn(lifecycleScope)
        }
    }

    open fun init() {}

    abstract fun renderActionState(actionState: BaseActionState?)

    private fun observerLoading() {
        lifecycleScope.launchWhenStarted {
            viewModel.loadingState.collect {
                if(it) progressBar.show()
                else progressBar.hide()
            }
        }
    }

    fun String.toast() {
        Toast.makeText(requireContext(), this, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressBar.hide()
        _binding = null
    }
}