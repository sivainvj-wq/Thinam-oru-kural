package com.thirukkural.app.ui.preface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thirukkural.app.R
import com.thirukkural.app.databinding.FragmentPrefaceBinding

class PrefaceFragment : Fragment() {

    private var _binding: FragmentPrefaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPrefaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToday.setOnClickListener {
            findNavController().navigate(R.id.navigation_today)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
