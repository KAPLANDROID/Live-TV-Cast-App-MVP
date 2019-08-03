package com.kaplandroid.castappmvp

import android.content.Context
import android.widget.Toast
import com.kaplandroid.castappmvp.cast.CastManager
import com.kaplandroid.castappmvp.cast.CastManagerContract
import com.kaplandroid.castappmvp.model.TvChannel

/**
 *
 */
class MainActivityPresenter(private val view: MainActivityContract.View) :
    MainActivityContract.Presenter, CastManagerContract {

    lateinit var castManager: CastManager

    override fun init() {
        view.bindData()
        view.initClickListeners()
        castManager = CastManager(view as Context, this)
    }

    override fun created() {
        view.initClickListeners()
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
        Toast.makeText(view as Context, "onCastError: $ch", Toast.LENGTH_SHORT).show()
    }

    override fun onCastSuccess(ch: TvChannel) {
        Toast.makeText(view as Context, "$ch", Toast.LENGTH_SHORT).show()
    }

    override fun onNoConnectedDevice() {
        view.noConnectedDevice()
    }

}
