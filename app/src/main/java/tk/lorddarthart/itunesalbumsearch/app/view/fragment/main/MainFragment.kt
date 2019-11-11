package tk.lorddarthart.itunesalbumsearch.app.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.app.App
import tk.lorddarthart.itunesalbumsearch.app.view.base.BaseFragment
import tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album.AboutAlbumFragment
import tk.lorddarthart.itunesalbumsearch.app.view.fragment.main.adapter.SearchAdapter
import tk.lorddarthart.itunesalbumsearch.databinding.FragmentMainBinding
import tk.lorddarthart.itunesalbumsearch.util.helper.IOnBackPressed

class MainFragment : BaseFragment(), MainFragmentView, IOnBackPressed {

    private lateinit var mainBinding: FragmentMainBinding
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter

    override fun triggerSearch(searchString: String) {
        if (!searchString.isBlank() && !mainFragmentPresenter.beginNetworkRequest) {
            mainFragmentPresenter.beginNetworkRequest = true
            coroutineScope.launch { request() }
        }
    }

    override fun triggerError(errorString: String) {
        errorString.let { message ->
            activity.runOnUiThread {
                Snackbar.make(mainBinding.root, message, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun triggerRecycler() {
        mainBinding.fragmentMainListOfUsersFound.adapter =
            SearchAdapter(
                mainFragmentPresenter.albumsList,
                mainFragmentPresenter.itemTouchListener!!
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )

        initialization()

        return mainBinding.root
    }

    private fun initialization() {
        start()
        initListeners()
        configure()
    }

    private fun configure() {
        // For RecyclerView's better performance
        with(mainBinding.fragmentMainListOfUsersFound) {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            isDrawingCacheEnabled = true
            drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        }
    }

    private fun start() {
        with(activity) {
            setSupportActionBar(mainBinding.toolbar)
        }

        mainFragmentPresenter.begin()

        mainBinding.fragmentMainListOfUsersFound.layoutManager = LinearLayoutManager(activity)
    }

    private fun initListeners() {
        mainBinding.fragmentMainButtonSearch.setOnClickListener {
            mainFragmentPresenter.searchString = mainBinding.fragmentMainSearchField.text.toString()
            coroutineScope.launch { request() }
        }
    }

    private fun showExitDialog() {
        MaterialAlertDialogBuilder(activity)
            .setTitle(getString(R.string.menu_exit))
            .setMessage(getString(R.string.exit_accept_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                activity.finishAffinity()
            }
            .setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private suspend fun request() {
        delay(600)
        mainFragmentPresenter.fetchData()
    }

    override fun onBackPressed() {
        showExitDialog()
    }

    override fun onClick(v: View?) {
        activity.supportFragmentManager.beginTransaction()
            .add(
                R.id.main_fragment_container,
                AboutAlbumFragment.newInstance(
                    mainFragmentPresenter.album!!,
                    mainFragmentPresenter.searchForTrackListString!!
                )
            ).addToBackStack(null)
            .commitAllowingStateLoss()
    }
}