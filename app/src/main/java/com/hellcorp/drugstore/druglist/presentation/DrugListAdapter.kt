package com.hellcorp.drugstore.druglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hellcorp.drugstore.R
import com.hellcorp.drugstore.databinding.ItemDrugInfoBinding
import com.hellcorp.drugstore.domain.models.Drug

class DrugListAdapter(private val clickListener: (Drug) -> Unit) :
    RecyclerView.Adapter<DrugListAdapter.DrugListViewHolder>() {
    inner class DrugListViewHolder(private val binding: ItemDrugInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val radius = itemView.resources.getDimension(R.dimen.radius_8dp)
        fun bind(data: Drug) = with(binding) {
            ivImage.load(data.imageUrl) {
                allowHardware(false)
                placeholder(R.drawable.ic_empty_search)
                error(R.drawable.ic_server_error)
                transformations(RoundedCornersTransformation(radius = radius))
            }
            tvTitle.text = data.name
            tvDescription.text = data.description
            itemView.setOnClickListener { this@DrugListAdapter.clickListener(data) }
        }
    }

    private val dataList = ArrayList<Drug>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDrugInfoBinding.inflate(layoutInflater, parent, false)
        return DrugListViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DrugListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setData(list: List<Drug>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataList.clear()
        dataList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearAll() {
        val diffCallback = DiffCallback(dataList, emptyList())
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        diffResult.dispatchUpdatesTo(this)
    }
}