package com.unicorn.sxmobileoa.app.mess

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

class DialogUitls{
    companion object {

        fun showMask(context: Context,title:String):MaterialDialog{
            return MaterialDialog.Builder(context)
                    .title(title)
                    .progress(true,100)
                    .show()

        }

        fun showMask2(context: Context,title:String):MaterialDialog{
            return MaterialDialog.Builder(context)
                    .title(title)
                    .progress(false,100)
                    .cancelable(false)
                    .show()

        }
    }
}