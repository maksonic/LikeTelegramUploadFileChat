package com.maksonic.liketelegramuploadfilechat.presentation.screen.chat

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.internal.ViewUtils.doOnApplyWindowInsets
import com.maksonic.liketelegramuploadfilechat.core.base.BaseScreen
import com.maksonic.liketelegramuploadfilechat.databinding.ScreenChatBinding
import com.maksonic.liketelegramuploadfilechat.navigation.Navigator
import org.koin.android.ext.android.inject

/**
 * @Author: maksonic on 25.12.2021
 */
class ChatScreen : BaseScreen<ScreenChatBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenChatBinding
        get() = ScreenChatBinding::inflate

    private val navigator: Navigator by inject()

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.messagePanel.sendFile {
            showUserFiles()
            binding.messagePanel.addSystemBottomPadding(binding.messagePanel)
            /*ViewCompat.setOnApplyWindowInsetsListener(binding.messagePanel) {view, insets ->
                view.updatePadding(bottom = insets.systemWindowInsetBottom)
                insets
            }*/
        }


    }


    private fun showUserFiles() {
        checkedPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val checkedPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navigator.showPickerSheet(this)
            }
        }
}

fun View.addSystemBottomPadding(
    targetView: View = this,
    isConsumed: Boolean = false
) {
    doOnApplyWindowInsets { _, insets, initialPadding ->
        targetView.updatePadding(
            bottom = initialPadding.bottom + insets.systemWindowInsetBottom
        )
        if (isConsumed) {
            insets.replaceSystemWindowInsets(
                Rect(
                    insets.systemWindowInsetLeft,
                    insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight,
                    0
                )
            )
        } else {
            insets
        }
    }
}

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    val initialPadding = recordInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

