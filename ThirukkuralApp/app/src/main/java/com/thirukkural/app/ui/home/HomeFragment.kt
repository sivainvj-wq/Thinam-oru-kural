package com.thirukkural.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.thirukkural.app.R
import com.thirukkural.app.databinding.FragmentHomeBinding
import com.thirukkural.app.model.Kural
import com.thirukkural.app.utils.PreferencesManager
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferencesManager(requireContext())

        // Set language tab default from saved preference
        if (prefs.getLanguage() == "ta") {
            binding.tabLanguage.getTabAt(1)?.select()
        }

        // Date header
        val dateFormat = SimpleDateFormat("EEEE, MMM d yyyy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(Date())

        // Check if opened from notification deep link
        val kuralNumber = arguments?.getInt("kuralNumber", -1) ?: -1
        if (kuralNumber > 0) {
            viewModel.loadKural(kuralNumber)
        } else {
            viewModel.loadTodayKural()
        }

        // Observe kural LiveData
        viewModel.currentKural.observe(viewLifecycleOwner) { kural ->
            kural?.let { displayKural(it) }
        }

        // Language tab toggle — re-display current kural in new language
        binding.tabLanguage.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.currentKural.value?.let { displayKural(it) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Share button
        binding.btnShare.setOnClickListener {
            viewModel.currentKural.value?.let { shareKural(it) }
        }
    }

    private fun displayKural(kural: Kural) {
        val isTamil = binding.tabLanguage.selectedTabPosition == 1
        val (l1, l2) = kural.getFormattedLines()

        binding.tvKuralNumber.text = getString(R.string.kural_number_format, kural.number)
        binding.tvKuralLine1.text = l1
        binding.tvKuralLine2.text = l2

        if (isTamil) {
            binding.tvBookName.text = kural.bookName
            binding.tvChapterLabel.text = getString(R.string.chapter_label_ta)
            binding.tvChapterName.text = "${kural.chapter}. ${kural.chapterName}"
            binding.tvTranslationLabel.text = getString(R.string.meaning_label_ta)
            binding.tvTranslation.text = kural.explanationTa
        } else {
            binding.tvBookName.text = kural.bookNameEn
            binding.tvChapterLabel.text = getString(R.string.chapter_label_en)
            binding.tvChapterName.text = "${kural.chapter}. ${kural.chapterNameEn}"
            binding.tvTranslationLabel.text = getString(R.string.meaning_label_en)
            binding.tvTranslation.text = "${kural.translationEn}\n\n${kural.explanationEn}"
        }
    }

    private fun shareKural(kural: Kural) {
        val isTamil = binding.tabLanguage.selectedTabPosition == 1
        val (l1, l2) = kural.getFormattedLines()
        val text = buildString {
            appendLine("திருக்குறள் #${kural.number}")
            appendLine(l1)
            appendLine(l2)
            appendLine()
            if (isTamil) {
                appendLine("பொருள்: ${kural.explanationTa}")
            } else {
                appendLine("Translation: ${kural.translationEn}")
                appendLine()
                appendLine("Meaning: ${kural.explanationEn}")
            }
            appendLine()
            appendLine("— Thirukkural App")
        }
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_kural)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
