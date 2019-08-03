package com.kaplandroid.castappmvp.cast

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadOptions
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.*
import com.google.android.gms.common.images.WebImage
import com.kaplandroid.castappmvp.db.TvChannelListDB
import com.kaplandroid.castappmvp.model.TvChannel
import java.util.*

/**
 *
 */
class CastManager(context: Context, private val castManagerContract: CastManagerContract) {

    private var mCastContext: CastContext = CastContext.getSharedInstance(context)

    private var mCastSession: CastSession? = null
    private var mSessionManager: SessionManager

    private val mSessionManagerListener = SessionManagerListenerImpl()

    init {
        mSessionManager = mCastContext.sessionManager
        mCastSession = mSessionManager.currentCastSession
    }

    private fun startCast(channel: TvChannel) {
        mCastSession = mSessionManager.currentCastSession

        if (mCastSession != null) {
            val movieMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE)

            movieMetadata.putString(MediaMetadata.KEY_TITLE, channel.name)
            movieMetadata.putString(
                MediaMetadata.KEY_SUBTITLE,
                Calendar.getInstance().time.toString()
            )
            movieMetadata.addImage(WebImage(Uri.parse("http://www.kaplandroid.com/kplndrd.png")))

            val mediaInfo = MediaInfo.Builder(channel.getStreamLink())
                .setStreamType(MediaInfo.STREAM_TYPE_LIVE)
                .setContentType("videos/mp4")
                .setMetadata(movieMetadata)
                .setStreamDuration(0)
                .build()
            val remoteMediaClient = mCastSession!!.remoteMediaClient
            if (remoteMediaClient != null) {
                remoteMediaClient.load(
                    mediaInfo, MediaLoadOptions.Builder()
                        .setAutoplay(true)
                        .setPlayPosition(0).build()
                )
                castManagerContract.onCastSuccess(channel)
            } else {
                castManagerContract.onNoConnectedDevice()
            }
        } else {
            castManagerContract.onNoConnectedDevice()
        }
    }

    fun activate() {
        mCastSession = mSessionManager.currentCastSession
        mSessionManager.addSessionManagerListener(mSessionManagerListener)
    }

    fun deactivate() {
        mSessionManager.removeSessionManagerListener(mSessionManagerListener)
        mCastSession = null
    }

    var lastIndex: Int = 0

    fun selectChannel(ch: TvChannel) {
        lastIndex = TvChannelListDB.channelList.indexOf(ch)
        Thread(Runnable {
            TvChannelListDB.updateTokens()
            Handler(Looper.getMainLooper()).post { startCast(ch) }
        }).start()
    }

    fun previousChannel() {
        lastIndex = if (lastIndex > 0) lastIndex - 1 else TvChannelListDB.channelList.size - 1
        Thread {
            TvChannelListDB.updateTokens()
            Handler(Looper.getMainLooper()).post { startCast(TvChannelListDB.channelList[lastIndex]) }
        }.start()
    }

    fun nextChannel() {
        lastIndex = if (lastIndex < TvChannelListDB.channelList.size - 1) lastIndex + 1 else 0
        Thread {
            TvChannelListDB.updateTokens()
            Handler(Looper.getMainLooper()).post { startCast(TvChannelListDB.channelList[lastIndex]) }
        }.start()
    }

    class SessionManagerListenerImpl : SessionManagerListener<Session> {

        override fun onSessionStarting(session: Session) {

        }

        override fun onSessionStarted(session: Session, sessionId: String) {
//        invalidateOptionsMenu()
        }

        override fun onSessionStartFailed(session: Session, i: Int) {

        }

        override fun onSessionEnding(session: Session) {

        }

        override fun onSessionResumed(session: Session, wasSuspended: Boolean) {
//        invalidateOptionsMenu()
        }

        override fun onSessionResumeFailed(session: Session, i: Int) {

        }

        override fun onSessionSuspended(session: Session, i: Int) {

        }

        override fun onSessionEnded(session: Session, error: Int) {
            //   finish();
        }

        override fun onSessionResuming(session: Session, s: String) {

        }


    }
}