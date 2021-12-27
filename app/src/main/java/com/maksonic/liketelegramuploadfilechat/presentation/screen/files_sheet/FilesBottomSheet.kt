package com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maksonic.liketelegramuploadfilechat.R
import com.maksonic.liketelegramuploadfilechat.core.base.BaseBottomSheet
import com.maksonic.liketelegramuploadfilechat.databinding.BottomSheetFilesBinding
import com.maksonic.liketelegramuploadfilechat.navigation.Navigator
import com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet.adapter.GalleryAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Author: maksonic on 25.12.2021
 */
class FilesBottomSheet : BaseBottomSheet<BottomSheetFilesBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetFilesBinding
        get() = BottomSheetFilesBinding::inflate
    private val viewModel: FilesBottomSheetViewModel by viewModel()
    private val navigator: Navigator by inject()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    private val galleryAdapter: GalleryAdapter by lazy {
        GalleryAdapter { image ->
            Toast.makeText(context, image?.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            initBottomSheetDialog(dialog as BottomSheetDialog)
        }
        return dialog
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.showGalleryImages()
        initRecyclerView()
        toolbarBackPressed()
        handleBottomFilePanel()
    }

    private fun getScreenHeight(): Int {
        return requireContext().resources.displayMetrics.heightPixels
    }

    private fun initBottomSheetDialog(dialog: BottomSheetDialog) {
        dialog.let {
            val bottomSheet = with(binding.root.rootView) {
                findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            }
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            val density = requireContext().resources.displayMetrics
            bottomSheetBehavior.peekHeight = (density.heightPixels * 0.6).toInt()

            val params = bottomSheet.layoutParams
            params.height = getScreenHeight()
            bottomSheet.layoutParams = params
            bottomSheet.background = requireContext().let {
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.bg_sheet,
                    requireContext().theme
                )
            }
            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            binding.bottomSheetLayout.transitionToStart()
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            binding.bottomSheetLayout.transitionToEnd()
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset > 0) {
                        binding.bottomSheetLayout.progress = slideOffset
                    }
                }
            })
        }

    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerGallery.adapter = galleryAdapter
           /* if (galleryAdapter.itemCount == galleryAdapter.currentList.size -1) {
                Toast.makeText(context, "END", Toast.LENGTH_SHORT).show()
            }*/
        }
        lifecycleScope.launch {
            viewModel.images.collect {
                galleryAdapter.submitList(it)
            }
        }
    }


    private fun toolbarBackPressed() {
        binding.toolBar.setNavigationOnClickListener {
            requireDialog().onBackPressed()
        }
    }


    private fun handleBottomFilePanel() {
        with(binding) {
            filesPanel.apply {
                filePanelAction(this.panelGallery) {
                    toolBar.title = requireContext().getString(R.string.panel_item_gallery)
                }
                filePanelAction(this.panelFile) {
                    toolBar.title = requireContext().getString(R.string.panel_item_file)
                }
                filePanelAction(this.panelLocation) {
                    toolBar.title = requireContext().getString(R.string.panel_item_location)
                }
                filePanelAction(this.panelContact) {
                    toolBar.title = requireContext().getString(R.string.panel_item_contact)
                }
                filePanelAction(this.panelMusic) {
                    toolBar.title = requireContext().getString(R.string.panel_item_music)
                }
            }
        }
    }
}
