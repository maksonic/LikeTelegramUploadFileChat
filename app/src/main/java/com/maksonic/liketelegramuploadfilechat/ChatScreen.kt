package com.maksonic.liketelegramuploadfilechat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maksonic.liketelegramuploadfilechat.databinding.ScreenChatBinding

/**
 * @Author: maksonic on 25.12.2021
 */
class ChatScreen : Fragment() {

    private var _binding: ScreenChatBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}