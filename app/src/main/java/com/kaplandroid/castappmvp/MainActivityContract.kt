package com.kaplandroid.castappmvp

import com.kaplandroid.castappmvp.model.TvChannel


interface MainActivityContract {

    interface View {
        fun bindData()

        fun initClickListeners()

        fun noConnectedDevice()

    }

    interface Presenter {

        fun init()

        fun created()

        fun onChannelSelected(ch: TvChannel)

        fun onBackClicked()

        fun onNextClicked()

        fun onResume()

        fun onPause()
    }

}
