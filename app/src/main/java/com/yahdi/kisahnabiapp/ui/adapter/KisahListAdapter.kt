package com.yahdi.kisahnabiapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yahdi.kisahnabiapp.R
import com.yahdi.kisahnabiapp.data.model.KisahResponse
import com.yahdi.kisahnabiapp.databinding.RowItemKisahBinding
import com.yahdi.kisahnabiapp.ui.activity.DetailActivity
import com.yahdi.kisahnabiapp.utils.KisahDiffUtils
import com.yahdi.kisahnabiapp.utils.Utils

class KisahListAdapter: RecyclerView.Adapter<KisahListAdapter.MyViewHolder>() {
    private var kisahList: ArrayList<KisahResponse> = arrayListOf()
    val data get() = kisahList

    class MyViewHolder(private val binding: RowItemKisahBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KisahResponse) {
            binding.apply {
                tvHeroName.text = root.context.getString(R.string.txt_kisah_hero, data.name)
                Glide.with(root.context)
                    .load(Utils.fromHttpToHttpsString(data.thumb))
                    .apply(RequestOptions())
                    .override(500, 500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(R.drawable.img_not_found)
                    .into(ivHeroThumb)

                root.apply {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.KISAH_DATA, data)

                    setOnClickListener {
                        context.startActivity(intent)
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                return MyViewHolder(
                    RowItemKisahBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(kisahList[position])
    }

    override fun getItemCount(): Int = kisahList.size

    fun setData(data: ArrayList<KisahResponse>) {
        val diffUtil = KisahDiffUtils(kisahList, data)
        val result = DiffUtil.calculateDiff(diffUtil)
        kisahList = data
        result.dispatchUpdatesTo(this)
    }
}