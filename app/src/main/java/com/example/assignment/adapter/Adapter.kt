package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.BR
import com.example.assignment.R
import com.example.assignment.database.ModelData
import com.example.assignment.databinding.ItemViewBinding


class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val modelDataList = mutableListOf<ModelData>()
    private lateinit var binding: ItemViewBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_view,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = modelDataList[position]
        (holder as ViewHolder).bind(data)
    }

    override fun getItemCount(): Int {
        return modelDataList.size
    }

    fun update(list:List<ModelData>){
        val cList = modelDataList+list
        val diff = cList.groupBy { it.code }
            .filter { it.value.size == 1 }
            .flatMap { it.value }
        val pos = modelDataList.size-1
        modelDataList.addAll(diff)

        notifyItemRangeChanged(pos,diff.size)
    }

    class ViewHolder(private val binding: ItemViewBinding) :RecyclerView.ViewHolder(binding.root){
        val dbID: TextView = itemView.findViewById<TextView>(R.id.dbID)
        val sampleText: TextView = itemView.findViewById<TextView>(R.id.sampleText)
        fun bind(obj:Any){
            binding.setVariable(BR.data,obj)
            binding.executePendingBindings()
        }
    }
}