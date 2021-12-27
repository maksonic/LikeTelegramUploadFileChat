package com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet.adapter

import coil.load
import com.maksonic.liketelegramuploadfilechat.core.base.BaseViewHolder
import com.maksonic.liketelegramuploadfilechat.databinding.ItemImageBinding
import com.maksonic.liketelegramuploadfilechat.domain.MediaStoreImage

/**
 * @Author: maksonic on 25.12.2021
 */
class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val onClick: ((MediaStoreImage?) -> Unit)? = null
) : BaseViewHolder<MediaStoreImage, ItemImageBinding>(binding) {
    init {
        binding.image.setOnClickListener {
            onClick?.invoke(getRowItem())
        }
    }

    override fun bind() {
        getRowItem()?.let {
            with(binding) {
                image.load(it.uri) {
                    crossfade(false)
                        .build()
                }
            }
        }
    }
}
