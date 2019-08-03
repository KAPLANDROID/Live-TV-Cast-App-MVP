package com.kaplandroid.castappmvp.db

import android.util.Log
import com.kaplandroid.castappmvp.model.TvChannel
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object TvChannelListDB {

    var token = ""
    var tms = ""

    var channelList = arrayOf(
        TvChannel("Kafa Radyo", "kafaradyo", "http://46.20.3.245/stream/510/"),
        TvChannel("Trt 1", "trt1"),
        TvChannel(
            "Kanal D",
            "kanald",
            "https://blutv-beta.akamaized.net/kanaldhd/kanaldhd.smil/chunklist_b2628000_slturk.m3u8"
            //https://2122248082.dogannet.tv/S2/HLS_LIVE/kanaldnp/track_4_1250/playlist.m3u8
        ),
        TvChannel("Show Tv", "showtv"),
        TvChannel("TV 8", "kanal8"),
        TvChannel("Star Tv", "startv"),
        TvChannel(
            "Fox Tv",
            "fox-turkiye-21",
            "https://blutv-beta.akamaized.net/foxtvhd/foxtvhd.smil/chunklist_b1096000_sltur.m3u8"
        ),
        TvChannel(
            "ATV",
            "atv",
            "https://trkvz-live.ercdn.net/atvhd/atvhd.m3u8?st=Mx1GJiXOz575i3a6e1BlIw&e=1564887572"
        ),
        TvChannel("DMAX Türkiye", "ntvspor"),
        TvChannel(
            "teve 2",
            "yeni-teve2-canli-izle",
            "https://teve2.dogannet.tv/S2/HLS_LIVE/teve2np/track_4_1000/playlist.m3u8"
        ),
        TvChannel("NTV", "ntv"),
        TvChannel(
            "CNN Türk",
            "cnnturk"
        ), //new TvChannel("CNN Türk2", "cnnturk2","https://live.dogannet.tv/S1/HLS_LIVE/cnn_turk/1000/prog_index.m3u8"),
        TvChannel("TLC TV", "tlctv"),
        TvChannel("360 TV", "skyturk"),
        TvChannel("TV 8,5", "tv8-buc-03"),
        TvChannel(
            "TRT World",
            "trtworld",
            "https://trtcanlitv-lh.akamaihd.net/i/TRTWORLD_1@321783/index_360p_av-p.m3u8?sd=10&rebase=on"
        ),
        TvChannel("Akıllı TV", "akillitv"),
        TvChannel("Halk Tv", "hd-halk-tv"),
        TvChannel("Bloomberg HT", "bloomberght"),
        TvChannel("Cartoon Network Türkiye", "cartoonnetwork"),
        TvChannel("Haber Global", "haberglobal"),
        TvChannel("TV 8 Int", "kanal8int"),
        TvChannel("Bein Sports Haber", "bein-sports-haber"),
        TvChannel("TRT Haber", "trthaber"),
        TvChannel("FB TV", "fbtv"),
        TvChannel("Sports TV", "sportstv"),
        TvChannel("TRT Belgesel", "trtbelgesel"),
        TvChannel("Habertürk", "haberturk"),
        TvChannel("Kanal 24", "kanal24"),
        TvChannel("Kanal 7", "kanal7"),
        TvChannel("Ulusal Kanal", "ulusalkanal"),
        TvChannel("A Para", "a-para"),
        TvChannel("Ülke TV", "ulketv"),
        TvChannel("TGRT Haber", "tgrthaber"),
        TvChannel("Tivibu Spor", "tivibuspor"),
        TvChannel("TRT Spor", "trt3spor"),
        TvChannel("A Spor", "aspor"),
        TvChannel("Show Max", "show-max"),
        TvChannel("Show Türk", "show-turk"),
        TvChannel("TJK TV", "tjktv"),
        TvChannel("Tay TV", "taytv"),
        TvChannel("A Haber", "ahaber"),
        TvChannel("A2 Tv", "a2tv"),
        TvChannel("ATV Avrupa", "atv-avrupa"),
        TvChannel("A News Türkiye", "anews"),
        TvChannel("TV Net", "tvnet"),
        TvChannel("Beyaz TV", "beyaztv"),
        TvChannel("Euro Star Türkçe", "euro-star"),
        TvChannel("TGRT EU", "tgrteu")
    )

    fun updateTokens() {

        val url1 =
            "http://web.canlitvlive.io/tvizle.php?css=eyJ6ZW1pbiI6IiMxQTFBMUEiLCJub3JlbmsiOiIjNEE4Q0I4Iiwib25pemxlIjoiMSIsImFkcmVuayI6IiNCMEQ1RTciLCJrYXRlZ29yaSI6InVuZGVmaW5lZCIsImFyYW1hdmFyIjoiMSIsImFkaG92ZXIiOiIjZmZmZmZmIiwiYWRyZW5rc2VjIjoiI0ZGRkZGRiJ9&t=2&pos=r&tv=ntv-spor-izle"

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url1)
            .build()

        try {
            val response = client.newCall(request).execute()
            val res = response.body()?.string()

            res?.let {
                token = it.split("tkn=")[1].split("&tms=")[0]
                tms = it.split("&tms=")[1].substring(0, 10)

                Log.e("res", "token:$token - tms:$tms")
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
