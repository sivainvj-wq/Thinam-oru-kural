package com.thirukkural.app.ui.kural

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thirukkural.app.R
import com.thirukkural.app.databinding.ItemKuralBinding
import com.thirukkural.app.model.Kural

class KuralListAdapter(
    private val isTamil: Boolean,
    private val onItemClick: (Kural) -> Unit
) : ListAdapter<Kural, KuralListAdapter.KuralViewHolder>(DiffCallback()) {

    inner class KuralViewHolder(private val binding: ItemKuralBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(kural: Kural) {
            binding.tvNumber.text = binding.root.context.getString(R.string.kural_number_simple, kural.number)
            binding.tvLine1.text = kural.line1
            binding.tvLine2.text = kural.line2
            binding.tvChapter.text = if (isTamil) kural.chapterName else kural.chapterNameEn

            // Color-code by book
            val bookColor = when (kural.book) {
                1 -> ContextCompat.getColor(binding.root.context, R.color.book_aram)
                2 -> ContextCompat.getColor(binding.root.context, R.color.book_porul)
                3 -> ContextCompat.getColor(binding.root.context, R.color.book_inbam)
                else -> ContextCompat.getColor(binding.root.context, R.color.primary)
            }
            binding.tvNumber.setTextColor(bookColor)

            binding.root.setOnClickListener { onItemClick(kural) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KuralViewHolder {
        val binding = ItemKuralBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return KuralViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KuralViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Kural>() {
        override fun areItemsTheSame(oldItem: Kural, newItem: Kural) =
            oldItem.number == newItem.number
        override fun areContentsTheSame(oldItem: Kural, newItem: Kural) =
            oldItem == newItem
    }
}
