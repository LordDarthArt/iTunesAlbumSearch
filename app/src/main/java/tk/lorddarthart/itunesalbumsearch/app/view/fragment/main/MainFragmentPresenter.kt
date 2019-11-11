package tk.lorddarthart.itunesalbumsearch.app.view.fragment.main

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.lorddarthart.itunesalbumsearch.app.model.Album
import tk.lorddarthart.itunesalbumsearch.app.model.ITunesAlbumResponse
import tk.lorddarthart.itunesalbumsearch.util.helper.OnItemTouchListener
import tk.lorddarthart.itunesalbumsearch.util.network.HttpServiceHelper
import java.util.*

/**
 * Created by LordDarthArt on 10.11.2019.
 */
@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {
    var album: Album? = null
    var itemTouchListener: OnItemTouchListener? = null
    var searchString: String? = null
    var beginNetworkRequest = false
    var searchForTrackListString: String? = null

    lateinit var albumsList: List<Album>

    fun fetchData() {
        GlobalScope.launch(Dispatchers.IO) {
            searchString?.let {
                HttpServiceHelper.instance?.jsonApi?.getAlbum(it, "album", "albumTerm")
                    ?.enqueue(object : Callback<ITunesAlbumResponse> {
                        override fun onResponse(
                            call: Call<ITunesAlbumResponse>,
                            response: Response<ITunesAlbumResponse>
                        ) {
                            val responseAlbumList = response.body()?.results
                            responseAlbumList?.let { albumList ->
                                albumsList = albumList.sortedBy {  it.collectionCensoredName.toString()  }
                            }
                            viewState.triggerRecycler()
                        }

                        override fun onFailure(call: Call<ITunesAlbumResponse>, t: Throwable) {
                            t.message?.let { it1 -> viewState.triggerError(it1) }
                        }
                    })
            }
        }
    }

    fun begin() {
        searchString = null
        beginNetworkRequest = false

        itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                val album = albumsList[position]
                searchForTrackListString = "${album.collectionId}"
                this@MainFragmentPresenter.album = album
                viewState.onClick(view)
            }
        }
    }
}