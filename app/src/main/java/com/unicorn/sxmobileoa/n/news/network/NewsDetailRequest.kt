package com.unicorn.sxmobileoa.n.news.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class NewsDetailRequest(contentId:String) : MaybeRequest(busiCode = "newsDetail"){

    init {
        addParameter(Key.contentId,contentId)
    }

}
