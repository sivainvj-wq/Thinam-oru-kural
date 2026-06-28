package com.thirukkural.app.ui.kural

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.thirukkural.app.R
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.databinding.FragmentKuralDetailBinding
import com.thirukkural.app.model.Kural
import com.thirukkural.app.utils.PreferencesManager
import kotlinx.coroutines.launch

class KuralDetailFragment : Fragment() {

    private var _binding: FragmentKuralDetailBinding? = null
    private val binding get() = _binding!!
    private var currentKural: Kural? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKuralDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kuralNumber = arguments?.getInt("kuralNumber", 1) ?: 1
        val prefs = PreferencesManager(requireContext())
        val isTamil = prefs.getLanguage() == "ta"

        // Set the default tab based on language preference
        binding.tabLanguage.getTabAt(if (isTamil) 1 else 0)?.select()

        val repo = KuralRepository(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            val kural = repo.getKuralByNumber(kuralNumber)
            currentKural = kural
            kural?.let {
                displayKural(it, binding.tabLanguage.selectedTabPosition == 1)
            }
        }

        binding.tabLanguage.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentKural?.let { displayKural(it, tab?.position == 1) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.btnShareDetail.setOnClickListener {
            currentKural?.let { shareKural(it) }
        }
    }

    private fun displayKural(kural: Kural, isTamil: Boolean) {
        val (l1, l2) = kural.getFormattedLines()
        binding.tvKuralNumber.text = getString(R.string.kural_number_format, kural.number)
        binding.tvLine1.text = l1
        binding.tvLine2.text = l2

        if (isTamil) {
            binding.tvBook.text = kural.bookName
            binding.tvChapter.text = "${kural.chapter}. ${kural.chapterName}"
            binding.tvTranslationLabel.text = getString(R.string.meaning_label_ta)
            binding.tvTranslation.text = kural.explanationTa
        } else {
            binding.tvBook.text = kural.bookNameEn
            binding.tvChapter.text = "${kural.chapter}. ${kural.chapterNameEn}"
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
