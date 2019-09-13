package com.kaplandroid.castappmvp.db

import android.util.Log
import com.kaplandroid.castappmvp.model.TvChannel
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object TvChannelListDB {

    var token = ""
    var tms = ""

    var channelList = arrayListOf(
        TvChannel("Kafa Radyo", "kafaradyo", "http://46.20.3.245/stream/510/"),
        TvChannel("Trt 1-", "trt1",



            "https://hdtvler1.etvserver.com/live_sd/trt1/chunks.m3u8?nimblesessionid=5757372&wmsAuthSign=c2VydmVyX3RpbWU9OS83LzIwMTkgODowNjoyMiBQTSZoYXNoX3ZhbHVlPVVxVnE1NTBiWGxFeDJPN0owSmROYkE9PSZ2YWxpZG1pbnV0ZXM9NQ=="

        ),




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
//            "https://blutv-beta.akamaized.net/atvhd/atvhd.smil/playlist.m3u8"
            "https://trkvz-m.ercdn.net/trkvz-temp/atvhdm.m3u8?wmsAuthSign=c2VydmVyX3RpbWU9NS8xMi8yMDE4IDEwOjExOjA2IFBNJmhhc2hfdmFsdWU9cGtzUElvZjBwOUFIcDZRenpxQmxLdz09JnZhbGlkbWludXRlcz01"
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
        TvChannel("TV 8,5", "kanal8b"),
        TvChannel(
            "TRT World",
            "trtworld",
            "https://trtcanlitv-lh.akamaihd.net/i/TRTWORLD_1@321783/index_360p_av-p.m3u8?sd=10&rebase=on"
        ),
        TvChannel("Akıllı TV", "akillitv"),
        TvChannel("Halk Tv", "halktv"),
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

    var lastUpdateTime = 0L

    fun updateTokens() {
        // 30 minutes cache for token (in milli seconds: 30 * 60 * 1000)
        if (System.currentTimeMillis() - lastUpdateTime > 30 * 60 * 1000) {
            val tokenRefreshUrl =
                "http://web.canlitvlive.io/tvizle.php?tv=cnnturk"

            val client = OkHttpClient()

            val request = Request.Builder()
                .url(tokenRefreshUrl)
                .build()

            try {
                val response = client.newCall(request).execute()
                val res = response.body()?.string()

                Log.e("res: ",res)

                res?.let {
                    token = it.split("tkn=")[1].split("&tms=")[0]
                    tms = it.split("&tms=")[1].substring(0, 10)

                    lastUpdateTime = System.currentTimeMillis()

                    Log.e("res", "token:$token - tms:$tms - lastUpdateTime:$lastUpdateTime")
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }else{
            Log.e("Token Refresh", "Token is up-to-date")
        }
    }
}
