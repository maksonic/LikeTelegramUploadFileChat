package com.maksonic.liketelegramuploadfilechat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.maksonic.liketelegramuploadfilechat.databinding.ViewPanelMessageBinding

/**
 * @Author: maksonic on 25.12.2021
 */
class MessagePanelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    private var binding: ViewPanelMessageBinding =
        ViewPanelMessageBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
    }
}