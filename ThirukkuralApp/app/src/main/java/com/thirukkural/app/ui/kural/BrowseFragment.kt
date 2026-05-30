package com.thirukkural.app.ui.kural

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thirukkural.app.R
import com.thirukkural.app.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BrowseViewModel by viewModels()
    private lateinit var adapter: KuralListAdapter
    private var isSearching = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isTamil = viewModel.getLanguage() == "ta"

        adapter = KuralListAdapter(isTamil) { kural ->
            val bundle = Bundle().apply { putInt("kuralNumber", kural.number) }
            findNavController().navigate(R.id.action_browse_to_detail, bundle)
        }

        binding.recyclerKurals.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerKurals.adapter = adapter

        // Observe all kurals
        viewModel.allKurals.observe(viewLifecycleOwner) { kurals ->
            if (!isSearching) {
                adapter.submitList(kurals)
                binding.tvCount.text = if (isTamil)
                    "மொத்தம் ${kurals.size} குறள்கள்"
                else
                    "${kurals.size} Kurals total"
            }
        }

        // Observe search results
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            if (isSearching) {
                adapter.submitList(results)
                binding.tvCount.text = if (isTamil)
                    "${results.size} குறள்கள் கண்டுபிடிக்கப்பட்டன"
                else
                    "${results.size} kurals found"
            }
        }

        // Search listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    isSearching = false
                    viewModel.allKurals.value?.let { adapter.submitList(it) }
                    binding.tvCount.text = if (isTamil)
                        "மொத்தம் ${viewModel.allKurals.value?.size ?: 0} குறள்கள்"
                    else
                        "${viewModel.allKurals.value?.size ?: 0} Kurals total"
                } else {
                    isSearching = true
                    viewModel.search(newText)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
