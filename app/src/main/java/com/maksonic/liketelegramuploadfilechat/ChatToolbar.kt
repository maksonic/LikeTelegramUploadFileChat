package com.maksonic.liketelegramuploadfilechat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.maksonic.liketelegramuploadfilechat.databinding.ToolbarChatBinding

/**
 * @Author: maksonic on 25.12.2021
 */
class ChatToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {
    private var binding: ToolbarChatBinding =
        ToolbarChatBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.btnBack.setOnClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}