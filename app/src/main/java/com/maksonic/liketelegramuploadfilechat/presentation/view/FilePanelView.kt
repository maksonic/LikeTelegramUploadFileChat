package com.maksonic.liketelegramuploadfilechat.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.maksonic.liketelegramuploadfilechat.R
import com.maksonic.liketelegramuploadfilechat.databinding.ViewPanelFileBinding

/**
 * @Author: maksonic on 25.12.2021
 */
interface FilePanelItem {
    val panelGallery: View
    val panelFile: View
    val panelLocation: View
    val panelContact: View
    val panelMusic: View
    val itemList: List<View>
}

class FilePanelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes), FilePanelItem {
    private var binding: ViewPanelFileBinding =
        ViewPanelFileBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 86)
        orientation = HORIZONTAL
        with(binding) {
            root.setBackgroundColor(context.getColor(R.color.design_default_color_on_primary))
            gallery.isSelected = true
        }
    }

    override val panelGallery = binding.gallery
    override val panelFile = binding.file
    override val panelLocation = binding.location
    override val panelContact = binding.contact
    override val panelMusic = binding.music
    override val itemList = listOf(panelGallery, panelFile, panelLocation, panelContact, panelMusic)

    fun filePanelAction(view: View, action: () -> Unit) {
        itemList.forEach { _ ->
            view.setOnClickListener {
                itemList.forEach {
                    it.isSelected = it == view
                }
                action.invoke()
            }
        }
    }

}