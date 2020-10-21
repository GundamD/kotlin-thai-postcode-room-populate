package com.gundamd.demo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gundamd.demo.databinding.ItemAddressBinding
import com.gundamd.thai_postcode_sdk.data.ThaiAddress

import kotlin.properties.Delegates

class AddressListAdapter() :
    RecyclerView.Adapter<AddressViewHolder>() {

    var addressList: List<ThaiAddress> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(AddressDiff(old, new), false).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressList.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(addressList[position])
    }
}

private class AddressDiff(private val old: List<ThaiAddress>, private val new: List<ThaiAddress>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].districtCode == new[newItemPosition].districtCode

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = old[oldItemPosition]
        val newItem = new[newItemPosition]
        return oldItem.district == newItem.district && oldItem.districtCode == newItem.districtCode
    }

}

class AddressViewHolder(private val binding: ItemAddressBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var postcode: String = ""

    fun bind(address: ThaiAddress) {
        binding.addressSubDistrict.text = address.subDistrict
        binding.addressDistrict.text = address.district
        binding.addressProvince.text = address.province
        binding.addressPostCode.text = address.zipcode
        postcode = address.zipcode
    }
}
