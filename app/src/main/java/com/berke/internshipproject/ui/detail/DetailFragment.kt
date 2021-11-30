package com.berke.internshipproject.ui.detail

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.berke.internshipproject.R
import com.berke.internshipproject.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import com.berke.internshipproject.base.BaseFragment
import com.berke.internshipproject.base.BaseActionState
import com.berke.internshipproject.base.setImageUrl
import com.berke.internshipproject.model.Favorites
import java.lang.Exception

@AndroidEntryPoint
class DetailFragment: BaseFragment<DetailViewModel, FragmentDetailBinding>(R.layout.fragment_detail, DetailViewModel::class.java) {

    override fun init() {
        super.init()

        arguments?.let {
            viewModel.imageURL = it.getString("imageURL")
            binding.detailImage.setImageUrl(viewModel.imageURL)
        }

        viewModel.imageURL?.let {
            if(viewModel.hasContains(it)) {
                binding.addFavorites.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
            } else {
                binding.addFavorites.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24)
            }
        }

        setOnClickListeners()
    }

    override fun renderActionState(actionState: BaseActionState?) {
        when(val state = actionState as? DetailActionState) {
            is DetailActionState.ShowToast -> state.message.toast()
            else -> {}
        }
    }

    private fun setOnClickListeners() {
        binding.addFavorites.setOnClickListener {
            viewModel.imageURL?.let { url ->
                try {
                    if (viewModel.hasContains(url)) {
                        viewModel.deleteImage(url)
                        binding.addFavorites.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_border_24
                        )
                    } else {
                        viewModel.insertImage(Favorites(url = url))
                        binding.addFavorites.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_24
                        )
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Hata", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}