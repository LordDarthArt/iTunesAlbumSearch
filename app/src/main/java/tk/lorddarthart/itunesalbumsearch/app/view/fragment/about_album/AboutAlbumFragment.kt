package tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.app.App
import tk.lorddarthart.itunesalbumsearch.app.model.Album
import tk.lorddarthart.itunesalbumsearch.app.view.base.BaseFragment
import tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album.adapter.TrackAdapter
import tk.lorddarthart.itunesalbumsearch.databinding.FragmentAboutAlbumBinding
import tk.lorddarthart.itunesalbumsearch.util.helper.UtilFunctions

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class AboutAlbumFragment: BaseFragment(), AboutAlbumFragmentView {

    private lateinit var aboutAlbumFragmentBinding: FragmentAboutAlbumBinding

    @InjectPresenter
    lateinit var aboutAlbumFragmentPresenter: AboutAlbumFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutAlbumFragmentBinding = FragmentAboutAlbumBinding.inflate(
            inflater,
            container,
            false
        )

        initialization()

        return aboutAlbumFragmentBinding.root
    }

    private fun initialization() {
        start()
    }

    private fun start() {
        aboutAlbumFragmentPresenter.album = album
        aboutAlbumFragmentPresenter.fullSearchAlbumTracksString = searchString

        aboutAlbumFragmentBinding.albumTrackList.layoutManager = LinearLayoutManager(App.instance)
        aboutAlbumFragmentBinding.albumTrackList.adapter = TrackAdapter(aboutAlbumFragmentPresenter.trackList)

        triggerFragment(aboutAlbumFragmentPresenter.album)
        aboutAlbumFragmentPresenter.fetchTrackData()

        activity.setSupportActionBar(aboutAlbumFragmentBinding.aboutAlbumToolbar)
        activity.supportActionBar?.title = aboutAlbumFragmentPresenter.album?.collectionCensoredName
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun triggerRecycler() {
        aboutAlbumFragmentBinding.albumTrackList.adapter?.notifyDataSetChanged()
    }

    override fun triggerError(error: String) {
        error.let { message ->
            activity.runOnUiThread {
                Snackbar.make(aboutAlbumFragmentBinding.root, message, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun triggerFragment(targetAlbum: Album?) {
        targetAlbum?.let { album ->
            with (aboutAlbumFragmentBinding) {
                albumArtist.text = "${App.instance.getString(R.string.artist)} ${album.artistName}"
                albumCopyright.text = album.copyright
                albumTitle.text = album.collectionCensoredName
                albumCost.text = "${App.instance.getString(R.string.price)} ${album.collectionPrice?.let {
                    "\$" + it
                } ?: App.instance.getString(R.string.free_price)}"

                try {
                    UtilFunctions.animatedImgLoad(album.artworkUrl100, aboutAlbumFragmentBinding.albumArt)
                } catch (e: Exception) {
                    Toast
                        .makeText(
                            App.instance,
                            e.message,
                            Toast.LENGTH_LONG
                        ).show()
                }
            }
        }
    }

    companion object {
        var album: Album? = null
        var searchString: String? = null

        fun newInstance(album: Album, searchString: String): AboutAlbumFragment {
            this.album = album
            this.searchString = searchString
            return AboutAlbumFragment()
        }
    }
}