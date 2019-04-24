package com.unicorn.sxmobileoa.n.process.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.ConvertUtils
import com.github.vipulasri.timelineview.TimelineView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.process.model.Process
import kotlinx.android.synthetic.main.item_process.*


class ProcessAdapter : RecyclerView.Adapter<ProcessViewHolder>() {

    private var mData: List<Process> = ArrayList()
    private lateinit var mContext: Context

    fun setNewData(data: List<Process>) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_process, parent, false)
        return ProcessViewHolder(view, viewType)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        holder.apply {
            time_marker.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorPrimary))
            val process = mData[position]
            val array = process.dealTime.split(" ")
            tvDealDate.text = array[0]
            tvDealTime.text = array[1]
            tvTaskName.text = process.taskName + " " + process.dealPersonName
            tvSpResult.text = process.spResult

            GradientDrawable().apply {
                setStroke(1, ContextCompat.getColor(mContext, R.color.md_grey_600))
                setColor(Color.WHITE)
                cornerRadius = ConvertUtils.dp2px(5f).toFloat()
            }.let { container.background = it  }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}
