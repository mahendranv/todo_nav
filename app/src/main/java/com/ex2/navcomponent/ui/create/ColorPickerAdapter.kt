package com.ex2.navcomponent.ui.create

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.GridColorItemBinding
import com.ex2.navcomponent.layoutInflater

class ColorPickerAdapter : RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>() {

    private val colors = ToDoRepository.COLORS

    var selectedColor = colors[0]

    inner class ViewHolder(private val binding: GridColorItemBinding, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var color: Int? = null

        init {
            itemView.setOnClickListener {
                color?.let {
                    selectedColor = it
                    notifyDataSetChanged()
                }
            }
        }

        fun bind(color: Int) {
            this.color = color
            binding.icon.apply {
                fillColor = color
                isSelected = selectedColor == color
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GridColorItemBinding.inflate(parent.layoutInflater(), parent, false)
        return ViewHolder(binding, binding.root)
    }

    override fun getItemCount() = colors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}