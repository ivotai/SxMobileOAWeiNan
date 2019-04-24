package com.unicorn.sxmobileoa.app.mess

import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.MaybeTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainThreadTransformer<Up>: MaybeTransformer<Up, Up> {

    override fun apply(upstream: Maybe<Up>): MaybeSource<Up> {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}