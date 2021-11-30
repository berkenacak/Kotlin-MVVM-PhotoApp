package com.berke.internshipproject.ui.list

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.berke.internshipproject.R
import com.berke.internshipproject.ui.list.adapter.PhotoAdapter
import com.berke.internshipproject.base.BaseActionState
import com.berke.internshipproject.base.BaseFragment
import com.berke.internshipproject.databinding.FragmentListBinding
import com.berke.internshipproject.model.ImageModel
import com.berke.internshipproject.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@AndroidEntryPoint
class ListFragment: BaseFragment<ListViewModel, FragmentListBinding>(
    R.layout.fragment_list, ListViewModel::class.java
) {

    private lateinit var adapter : PhotoAdapter

    override fun init() {
        super.init()

        adapter = PhotoAdapter(::navigateDetail)
        binding.photoListRecyclerView.adapter = adapter
        binding.photoListRecyclerView.setHasFixedSize(true)

        viewModel.photos.observe(viewLifecycleOwner) {
            if(it is List<ImageModel>) {
                adapter.submitList(it as MutableList<ImageModel>)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.searchView.setText("")
            viewModel.getPhotos("")
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.searchView.getQueryTextChangeStateFlow()
            .debounce(300)
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
            .onEach { query->
                viewModel.getPhotos(query)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun renderActionState(actionState: BaseActionState?) {
        when(val state = actionState as? ActionState) {
            ActionState.Init -> Unit
            is ActionState.ShowErrorDialog -> state.message.toast()
            ActionState.ShowingPhotos -> "ShowingPhotos".toast()
            else -> {}
        }
    }

    private fun navigateDetail(imageModel: ImageModel) {
        val bundle = Bundle()

        bundle.putString("imageURL", imageModel.largeImageURL)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }
}