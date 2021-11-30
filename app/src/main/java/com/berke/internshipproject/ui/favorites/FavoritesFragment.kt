package com.berke.internshipproject.ui.favorites

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.berke.internshipproject.R
import com.berke.internshipproject.ui.favorites.adapter.FavoritesAdapter
import com.berke.internshipproject.base.BaseActionState
import com.berke.internshipproject.base.BaseFragment
import com.berke.internshipproject.databinding.FragmentFavoritesBinding
import com.berke.internshipproject.model.Favorites
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(
    R.layout.fragment_favorites, FavoritesViewModel::class.java
) {

    private lateinit var adapter: FavoritesAdapter

    override fun init() {
        super.init()

        adapter = FavoritesAdapter(::navigateDetail)
        binding.rcFavorites.adapter = adapter
        binding.rcFavorites.setHasFixedSize(true)

        viewModel.allImages.observe(viewLifecycleOwner) {
            if (it is List<Favorites>) {
                adapter.submitList(it as MutableList<Favorites>)
            }
        }

        viewModel.getFavoritePhotos()
    }

    override fun renderActionState(actionState: BaseActionState?) {
        when(val state = actionState as? FavoritesActionState) {
            is FavoritesActionState.ShowMessage -> state.message.toast()
            else -> {}
        }
    }

    private fun navigateDetail(favorites: Favorites) {
        val bundle = Bundle()
        bundle.putString("imageURL", favorites.url)
        findNavController().navigate(R.id.action_favoritesFragment_to_detailFragment2, bundle)
    }

}