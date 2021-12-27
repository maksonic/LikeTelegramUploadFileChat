package com.maksonic.liketelegramuploadfilechat.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

/**
 * @Author: maksonic on 25.12.2021
 */
class SafeNavigation {

    companion object {
        private const val PASSED_DATA = "passed data"
    }

    fun safeNavigate(
        fragment: Fragment,
        actionId: Int,
        hostId: Int? = null,
        data: Parcelable? = null
    ) {
        val navController = if (hostId == null) {
            fragment.findNavController()
        } else {
            Navigation.findNavController(fragment.requireActivity(), hostId)
        }
        val bundle = Bundle().apply { putParcelable(PASSED_DATA, data) }
        val action = navController.currentDestination?.getAction(actionId)

        if (action != null && navController.currentDestination?.id != action.destinationId) {
            navController.navigate(actionId, bundle)
        }
    }
}

internal const val PASSED_DATA = "passed data"
internal val Fragment.navigationData: Parcelable?
    get() = arguments?.getParcelable(PASSED_DATA)
