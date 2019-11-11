package tk.lorddarthart.itunesalbumsearch.app.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arellomobile.mvp.presenter.InjectPresenter
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.app.view.base.BaseActivity
import tk.lorddarthart.itunesalbumsearch.app.view.fragment.main.MainFragment
import tk.lorddarthart.itunesalbumsearch.databinding.ActivityMainBinding
import tk.lorddarthart.itunesalbumsearch.util.helper.IOnBackPressed
import tk.lorddarthart.itunesalbumsearch.util.logs.Loggable

class MainActivity : BaseActivity(), MainActivityView, Loggable {
    private lateinit var mainActivityView: ActivityMainBinding

    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    fun inflateMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(mainActivityView.mainFragmentContainer.id, MainFragment())
            .commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityView = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        initialization()
    }

    private fun initialization() {
        start()
    }

    private fun start() {
        inflateMainFragment()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        if (currentFragment is IOnBackPressed) {
            currentFragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}
