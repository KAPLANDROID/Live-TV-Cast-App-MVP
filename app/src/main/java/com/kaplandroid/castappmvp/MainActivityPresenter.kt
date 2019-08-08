package com.kaplandroid.castappmvp

import android.content.Context
import com.kaplandroid.castappmvp.cast.CastManager
import com.kaplandroid.castappmvp.cast.CastManagerContract
import com.kaplandroid.castappmvp.db.TvChannelListDB
import com.kaplandroid.castappmvp.model.TvChannel

/**
 *
 */
class MainActivityPresenter(private val view: MainActivityContract.View) :
    MainActivityContract.Presenter, CastManagerContract {

    private lateinit var castManager: CastManager

    override fun init() {
        castManager = CastManager(view as Context, this)
        view.initClickListeners()
    }

    override fun getTvChannelList() {
        view.bindData(TvChannelListDB.channelList)
    }

    override fun onChannelSelected(ch: TvChannel) {
        castManager.selectChannel(ch)
    }

    override fun onBackClicked() {
        castManager.previousChannel()
    }

    override fun onNextClicked() {
        castManager.nextChannel()
    }

    override fun onResume() {
        castManager.activate()
    }

    override fun onPause() {
        castManager.deactivate()
    }

    override fun onCastError(ch: TvChannel) {
        view.onCastResult(false, ch)
    }

    override fun onCastSuccess(ch: TvChannel) {
        view.onCastResult(true, ch)
    }

    override fun onNoConnectedDevice() {
        view.noConnectedDevice()
    }

}
