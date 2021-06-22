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


                    "https://blutv-beta.akamaized.net/trt1hd/trt1hd.smil/chunklist_b2596000_sltur.m3u8"

            ),


            TvChannel(
                    "Kanal D",
                    "kanald",
                    //"https://blutv-beta.akamaized.net/kanaldhd/kanaldhd.smil/chunklist_b2628000_slturk.m3u8"
                    "https://2122248082.dogannet.tv/S2/HLS_LIVE/kanaldnp/track_4_1250/playlist.m3u8"
            ),
            TvChannel(
                    "Show Tv",
                    "showtv",
                    "https://blutv-beta.akamaized.net/showhd/showtvhd.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel(
                    "TV 8",
                    "kanal8",
                    "https://blutv-beta.akamaized.net/tv8hd/tv8hd.smil/chunklist_b1596000_sltur.m3u8"
            ),
            TvChannel(
                    "Star Tv",
                    "startv",
                    "https://blutv-beta.akamaized.net/startvhd/startvhd.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel(
                    "Fox Tv",
                    "fox-turkiye-21",
                    "https://blutv-beta.akamaized.net/foxtvhd/foxtvhd.smil/chunklist_b1096000_sltur.m3u8"
            ),
            TvChannel(
                    "ATV",
                    "atv",
//            "https://blutv-beta.akamaized.net/atvhd/atvhd.smil/playlist.m3u8"
//            "https://trkvz-m.ercdn.net/trkvz-temp/atvhdm.m3u8?wmsAuthSign=c2VydmVyX3RpbWU9NS8xMi8yMDE4IDEwOjExOjA2IFBNJmhhc2hfdmFsdWU9cGtzUElvZjBwOUFIcDZRenpxQmxLdz09JnZhbGlkbWludXRlcz01"
                    "https://blutv-beta.akamaized.net/atvhd/atvhd.smil/chunklist_b1628000_sltur.m3u8"
            ),
            TvChannel(
                    "FX Türkiye",
                    "fxtr",
                    "https://blutv-beta.akamaized.net/fx/fx.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel("DMAX Türkiye", "ntvspor",
                    "https://jviqfbc2.rocketcdn.com/dmax.smil/chunklist_b2328000_sltur.m3u8"),
            TvChannel(
                    "teve 2",
                    "yeni-teve2-canli-izle",
//                    "https://teve2.dogannet.tv/S2/HLS_LIVE/teve2np/track_4_1000/playlist.m3u8"
                    "https://blutv-beta.akamaized.net/teve2hd/teve2hd.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel(
                    "NTV",
                    "ntv",
                    "https://blutv-beta.akamaized.net/ntvhd/ntvhd.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel(
                    "CNN Türk",
                    "cnnturk",
                    "https://blutv-beta.akamaized.net/cnnturkhd/cnnturkhd.smil/chunklist.m3u8"
            ), //new TvChannel("CNN Türk2", "cnnturk2","https://live.dogannet.tv/S1/HLS_LIVE/cnn_turk/1000/prog_index.m3u8"),
            TvChannel("TLC TV", "tlctv",
                    "https://pa9agxjg.rocketcdn.com/tlctv.smil/chunklist_b2328000_sltur.m3u8"),
            TvChannel("360 TV", "skyturk",
                    "https://mn-nl.mncdn.com/360tv_live/smil:360tv.smil/playlist.m3u8"
            ),
            TvChannel(
                    "TV 8,5",
                    "kanal8b",
                    "https://blutv-beta.akamaized.net/tv8.5hd/tv8.5hd.smil/chunklist_b1596000_sltur.m3u8"
            ),
            TvChannel(
                    "TRT World",
                    "trtworld",
                    "https://trtcanlitv-lh.akamaihd.net/i/TRTWORLD_1@321783/index_360p_av-p.m3u8?sd=10&rebase=on"
            ),
            TvChannel("Akıllı TV", "akillitv"),
            TvChannel("Halk Tv", "halktv",
                    "https://blutv-beta.akamaized.net/halktv/halktv.smil/chunklist.m3u8"
            ),
            TvChannel("Bloomberg HT", "bloomberght",
                    "https://blutv-beta.akamaized.net/bloomberghd/bloomberghd.smil/chunklist_b1096000_sltur.m3u8"
                    ),
            TvChannel("Cartoon Network Türkiye", "cartoonnetwork"),
            TvChannel("Haber Global", "haberglobal",
                    "https://stream-10.ix7.dailymotion.com/sec(mXyIj4CBCQvohplCKOf--DGr4k-LA6gK1u4NksDZoNw)/dm/3/x7df5ih/d/live-4.m3u8"
            ),
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
            TvChannel("TGRT Haber", "tgrthaber",
                    "https://5ab9557ae2fa9.streamlock.net/tgrt_haber/amlst:live-25/chunklist_w479861623_b1200096.m3u8"),
            TvChannel("Tivibu Spor", "tivibuspor"),
            TvChannel("TRT Spor", "trt3spor"),
            TvChannel("A Spor", "aspor",
                    "https://blutv-beta.akamaized.net/aspor/aspor.smil/chunklist_b1628000_sltur.m3u8"
            ),
            TvChannel("Show Max", "show-max"),
            TvChannel("Show Türk", "show-turk"),
            TvChannel("TJK TV", "tjktv"),
            TvChannel("Tay TV", "taytv"),
            TvChannel("A Haber", "ahaber",
                    "https://blutv-beta.akamaized.net/ahaber/ahaber.smil/chunklist_b1628000_sltur.m3u8"
            ),
            TvChannel("A2 Tv", "a2tv",
                    "https://blutv-beta.akamaized.net/a2/a2.smil/chunklist_b2628000_sltur.m3u8"
            ),
            TvChannel("ATV Avrupa", "atv-avrupa"),
            TvChannel("A News Türkiye", "anews"),
            TvChannel("TV Net", "tvnet"),
            TvChannel("Beyaz TV", "beyaztv"),
            TvChannel("Euro Star Türkçe", "euro-star"),
            TvChannel("tv100", "tv100"),
            TvChannel("80s", "euro-star","https://s3.eu-west-2.amazonaws.com/www.omerkaplan.com/master1080p.m3u8"),
            TvChannel("TGRT EU", "tgrteu")
    )

    var lastUpdateTime = 0L

    fun updateTokens() {
        // 30 minutes cache for token (in milli seconds: 30 * 60 * 1000)
        if (System.currentTimeMillis() - lastUpdateTime > 30 * 60 * 1000) {
            Log.e("Token Refresh", "Fetching new Token")
            val tokenRefreshUrl =
                    "http://web.canlitvlive.io/tvizle.php?tv=cnnturk"

            val client = OkHttpClient()

            val request = Request.Builder()
                    .url(tokenRefreshUrl)
                    .build()

            try {
                val response = client.newCall(request).execute()
                val res = response.body()?.string()

                Log.e("res: ", res)

                res?.let {
                    token = it.split("tkn=")[1].split("&tms=")[0]
                    tms = it.split("&tms=")[1].substring(0, 10)

                    lastUpdateTime = System.currentTimeMillis()

                    Log.e("res", "token:$token - tms:$tms - lastUpdateTime:$lastUpdateTime")
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Log.e("Token Refresh", "Token is up-to-date")
        }
    }
}
