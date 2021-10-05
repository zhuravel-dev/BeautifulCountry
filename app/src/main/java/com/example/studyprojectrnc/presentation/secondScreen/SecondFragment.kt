package com.example.studyprojectrnc.presentation.secondScreen

import android.app.Dialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import com.example.studyprojectrnc.domain.DataState
import com.example.studyprojectrnc.presentation.ImageDetailScreenDialog
import com.example.studyprojectrnc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*
import timber.log.Timber

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    private val viewModel: SecondFragmentVM by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecondBinding
        get() = FragmentSecondBinding::inflate
    private val imageDialogDetail = ImageDetailScreenDialog(this)
    private val customAdapter by lazy { ImageAdapter() }

    private fun initAdapter() {
        customAdapter.onItemClick = { largeImage ->
            println("${largeImage.largeImageURL}")
            imageDialogDetail.startShowing(largeImage.largeImageURL, largeImage.views)
        }
        Timber.i("In fun init adapter")
        binding.rcView.adapter = customAdapter
    }

    override fun setup() {
        initAdapter()
        initObservers(); viewModel.onTriggerEvent(SecondFragmentIntent.GetAllImages)
    }

    private fun initObservers() {
        viewModel.imagesLiveData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Empty -> {
                    Timber.i("DataState is empty")
                    createEmptyDialog()
                }
                is DataState.Error -> {
                    Timber.i("Error in DataState")
                    createErrorDialog()
                }
                is DataState.Loading -> {
                    progressBarView.max = 10
                }
                is DataState.Success -> {
                    customAdapter.submitList(dataState.data.listMM)
                }
            }
        }
    }

    private fun createErrorDialog(): Dialog {
        return activity?.let {
            val builder = androidx.appcompat.app.AlertDialog.Builder(it)
            builder.setMessage(R.string.Error)
                .setPositiveButton(R.string.OK,
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.create()
        } ?: throw IllegalStateException("Cannot be null")
    }

    private fun createEmptyDialog(): Dialog {
        return activity?.let {
            val builder = androidx.appcompat.app.AlertDialog.Builder(it)
            builder.setMessage(R.string.Error)
                .setPositiveButton(R.string.OK,
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.create()
        } ?: throw IllegalStateException("Cannot be null")
    }
}