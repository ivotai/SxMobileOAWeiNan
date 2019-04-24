package com.unicorn.sxmobileoa.n.process.ui

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.MyHolder
import kotlinx.android.synthetic.main.item_process.*

/**
 * Created by HP-HP on 05-12-2015.
 */
class ProcessViewHolder(itemView: View, viewType: Int) : MyHolder(itemView) {

//    @BindView(R.id.text_timeline_date)
//    internal var mDate: TextView? = null
//    @BindView(R.id.text_timeline_title)
//    internal var mMessage: TextView? = null
//    @BindView(R.id.time_marker)
//    internal var mTimelineView: TimelineView? = null

    init {
        time_marker.initLine(viewType)
    }
}
