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
import com.kaplandroid.castappmvp.BuildConfig
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

    private var lastChannelIndex: Int = 0

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
            movieMetadata.addImage(WebImage(Uri.parse(BuildConfig.logoUrl)))

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

    fun selectChannel(ch: TvChannel) {
        lastChannelIndex = TvChannelListDB.channelList.indexOf(ch)
        Thread(Runnable {
            TvChannelListDB.channelList[lastChannelIndex].customLink
                ?: TvChannelListDB.updateTokens()

            Handler(Looper.getMainLooper()).post { startCast(ch) }
        }).start()
    }

    fun previousChannel() {
        lastChannelIndex =
            if (lastChannelIndex > 0) lastChannelIndex - 1 else TvChannelListDB.channelList.size - 1

        selectChannel(TvChannelListDB.channelList[lastChannelIndex])
    }

    fun nextChannel() {
        lastChannelIndex =
            if (lastChannelIndex < TvChannelListDB.channelList.size - 1) lastChannelIndex + 1 else 0

        selectChannel(TvChannelListDB.channelList[lastChannelIndex])
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