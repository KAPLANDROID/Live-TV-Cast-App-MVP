package com.kaplandroid.castappmvp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.IntroductoryOverlay
import com.kaplandroid.castappmvp.model.TvChannel

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @BindView(R.id.gvChannelList)
    lateinit var gvChannelList: GridView

    @BindView(R.id.btnBack)
    lateinit var btnBack: Button

    @BindView(R.id.btnNext)
    lateinit var btnNext: Button

    private lateinit var mPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        this.mPresenter = MainActivityPresenter(this)
        this.mPresenter.init()

        this.mPresenter.getTvChannelList()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onPause() {
        mPresenter.onPause()
        super.onPause()
    }

    override fun bindData(chList: ArrayList<TvChannel>) {
        gvChannelList.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            chList
        )
    }

    override fun initClickListeners() {
        this.btnBack.setOnClickListener {
            this.mPresenter.onBackClicked()
        }
        this.btnNext.setOnClickListener {
            this.mPresenter.onNextClicked()
        }

        this.gvChannelList.setOnItemClickListener { parent, _, position, _ ->
            this.mPresenter.onChannelSelected(parent.adapter.getItem(position) as TvChannel)
        }
    }

    override fun noConnectedDevice() {
        showIntroductoryOverlay()
    }

    override fun onCastResult(isSuccess: Boolean, tvChannel: TvChannel) {
        if (isSuccess) {
            Toast.makeText(this, "Now Playing: $tvChannel", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Cast Error: $tvChannel", Toast.LENGTH_SHORT).show()
        }
    }

    private var mediaRouteMenuItem: MenuItem? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.browse, menu)
        mediaRouteMenuItem = CastButtonFactory.setUpMediaRouteButton(
            applicationContext, menu, R.id.media_route_menu_item
        )
        return true
    }

    private var mIntroductoryOverlay: IntroductoryOverlay? = null

    private fun showIntroductoryOverlay() {
        mIntroductoryOverlay?.remove()

        if (mediaRouteMenuItem != null && mediaRouteMenuItem!!.isVisible) {
            mIntroductoryOverlay = IntroductoryOverlay.Builder(
                this, mediaRouteMenuItem
            )
                .setTitleText(getString(R.string.info_connect_to_cast_device))
                .setOnOverlayDismissedListener { mIntroductoryOverlay = null }
                .build()
            mIntroductoryOverlay!!.show()
        } else {
            Toast.makeText(this, getString(R.string.error_no_nearby_device), Toast.LENGTH_SHORT)
                .show()
        }
    }
}