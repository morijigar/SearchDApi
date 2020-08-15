package com.example.apifetch.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apifetch.DetailActivityActivity
import com.example.apifetch.databinding.ItemPostsBinding
import com.example.apifetch.models.Datum

/**
 * This class is used to show each row of pass
 */
class PostAdapter(
    private val context: Context,
    private val data: MutableList<Datum>
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemPostsBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val model = data[position]
        holder.binding.model = model
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            val fImage = model.firstImage
            val intent = Intent(context, DetailActivityActivity::class.java)
            intent.putExtra("bundle_data", model)
            intent.putExtra("bundle_image", fImage)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(data: MutableList<Datum>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * View holder to hold individual row
     */
    inner class ViewHolder //binding.rlRoot.setOnClickListener(this);
        (var binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}