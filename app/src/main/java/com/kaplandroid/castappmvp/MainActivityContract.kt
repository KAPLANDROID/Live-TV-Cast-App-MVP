package com.kaplandroid.castappmvp

import com.kaplandroid.castappmvp.model.TvChannel


interface MainActivityContract {

    interface View {
        fun bindData(chList: ArrayList<TvChannel>)

        fun initClickListeners()

        fun noConnectedDevice()

        fun onCastResult(isSuccess: Boolean, tvChannel: TvChannel)

    }

    interface Presenter {

        fun init()

        fun getTvChannelList()

        fun onChannelSelected(ch: TvChannel)

        fun onBackClicked()

        fun onNextClicked()

        fun onResume()

        fun onPause()
    }

}
