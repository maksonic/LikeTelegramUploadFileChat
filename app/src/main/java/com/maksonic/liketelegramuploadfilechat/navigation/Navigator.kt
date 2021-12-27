package com.maksonic.liketelegramuploadfilechat.navigation

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.maksonic.liketelegramuploadfilechat.R

/**
 * @Author: maksonic on 25.12.2021
 */
interface Navigator {

    fun showPickerSheet(fragment: Fragment)
    fun selectImage(fragment: Fragment, imageData: Parcelable?)

    class Base(private val safeNav: SafeNavigation) : Navigator {
        override fun showPickerSheet(fragment: Fragment) {
            safeNav.safeNavigate(
                fragment,
                R.id.action_chatScreen_to_filesBottomSheet
            )
        }

        override fun selectImage(fragment: Fragment,imageData: Parcelable?) {
            safeNav.safeNavigate(
                fragment,
                R.id.action_filesBottomSheet_to_chatScreen,
                data = imageData
            )
        }
    }
}
