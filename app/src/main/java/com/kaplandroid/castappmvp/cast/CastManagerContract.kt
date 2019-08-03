package com.kaplandroid.castappmvp.cast

import com.kaplandroid.castappmvp.model.TvChannel

interface CastManagerContract {

    fun onNoConnectedDevice()

    fun onCastError(ch: TvChannel)

    fun onCastSuccess(ch: TvChannel)

}