package com.maksonic.liketelegramuploadfilechat.presentation.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.constraintlayout.widget.ConstraintSet
import com.maksonic.liketelegramuploadfilechat.R
import com.maksonic.liketelegramuploadfilechat.databinding.ViewPanelMessageBinding

/**
 * @Author: maksonic on 25.12.2021
 */
class MessagePanelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : MotionLayout(context, attrs, defStyle) {
    private var binding: ViewPanelMessageBinding =
        ViewPanelMessageBinding.inflate(LayoutInflater.from(context), this)
    private var messageTransition: MotionScene.Transition? = null
    init {
        this.setTransition(R.id.messageTransition)
        this.transitionToEnd()
        animation()

        showSendMessageBtn(this)
        binding.sendGif.setOnClickListener {
            this.transitionToEnd()
        }

    }




    private fun animation() {


    }

    fun sendFile(action:() -> Unit) {
        binding.sendFile.setOnClickListener {
            action.invoke()
        }
    }

    private fun showSendMessageBtn(parentLayout: MotionLayout) =
        binding.messageInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! <= 0) {
                    parentLayout.transitionToEnd()
                    Toast.makeText(context, "END animation", Toast.LENGTH_SHORT).show()
                }
                if (p0?.length!! > 0) {
                    parentLayout.transitionToStart()
                    Toast.makeText(context, "Start animation", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
/*  private fun showSendMessageBtn() {
        binding.messageInput.doOnTextChanged { text, start, before, count ->
            if (before > 0) {
                Toast.makeText(context, "START animation", Toast.LENGTH_SHORT).show()
            }
            if (before <= 0) {
                Toast.makeText(context, "END animation", Toast.LENGTH_SHORT).show()

            }
        }
    }*/
}