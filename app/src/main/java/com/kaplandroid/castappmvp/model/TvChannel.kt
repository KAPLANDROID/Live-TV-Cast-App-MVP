package com.kaplandroid.castappmvp.model

import android.util.Log
import com.kaplandroid.castappmvp.db.TvChannelListDB

class TvChannel(val name: String, private val keyword: String) {

    private var customLink: String? = null

    constructor(name: String, keyword: String, customLink: String) : this(name, keyword) {
        this.customLink = customLink
    }

    override fun toString(): String {
        return if (customLink == null)
            name
        else
            "$name *"
    }

    fun getStreamLink(): String {
        val link: String = if (customLink != null) {
            customLink!!
        } else {
            "http://ch.canlitvlive.io/$keyword/live.m3u8?tkn=${TvChannelListDB.token}&tms=${TvChannelListDB.tms}"
        }
        Log.e("link", link)

        return link
    }
}