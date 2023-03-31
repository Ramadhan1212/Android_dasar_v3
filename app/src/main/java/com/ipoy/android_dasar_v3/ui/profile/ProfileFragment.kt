package com.ipoy.android_dasar_v3.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.ipoy.android_dasar_v3.databinding.FragmentProfileBinding
import com.ipoy.android_dasar_v3.ui.profile.about.AboutActivity
import com.ipoy.android_dasar_v3.ui.profile.theme.ThemeActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        binding.apply {
            val theme:CardView = cvTheme
            val about:CardView = cvAbout
            theme.setOnClickListener {
                val i = Intent(requireContext(), ThemeActivity::class.java)
                startActivity(i)
            }

            about.setOnClickListener {
                val i = Intent(requireContext(), AboutActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}