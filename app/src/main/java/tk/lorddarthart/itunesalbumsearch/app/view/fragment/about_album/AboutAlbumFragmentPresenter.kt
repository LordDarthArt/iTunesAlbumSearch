package tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.lorddarthart.itunesalbumsearch.app.model.Album
import tk.lorddarthart.itunesalbumsearch.app.model.ITunesAlbumResponse
import tk.lorddarthart.itunesalbumsearch.app.model.ITunesTrackResponse
import tk.lorddarthart.itunesalbumsearch.app.model.Track
import tk.lorddarthart.itunesalbumsearch.app.view.base.BasePresenter
import tk.lorddarthart.itunesalbumsearch.util.network.HttpServiceHelper

/**
 * Created by LordDarthArt on 10.11.2019.
 */
@InjectViewState
class AboutAlbumFragmentPresenter : BasePresenter<AboutAlbumFragmentView>() {
    var album: Album? =  null
    var fullSearchAlbumTracksString: String? = null
    var trackList = mutableListOf<Track>()

    fun fetchTrackData() {
        trackList.clear()
        GlobalScope.launch(Dispatchers.IO) {
            fullSearchAlbumTracksString?.let {
                HttpServiceHelper.instance?.jsonApi?.getTracks(it, "song")
                    ?.enqueue(object : Callback<ITunesTrackResponse> {
                        override fun onResponse(
                            call: Call<ITunesTrackResponse>,
                            response: Response<ITunesTrackResponse>
                        ) {
                            val responseAlbumList = response.body()?.results
                            responseAlbumList?.let { trackList ->
                                this@AboutAlbumFragmentPresenter.trackList.addAll(trackList)
                            }
                            trackList.removeAt(0)
                            viewState.triggerRecycler()
                        }

                        override fun onFailure(call: Call<ITunesTrackResponse>, t: Throwable) {
                            t.message?.let { it1 -> viewState.triggerError(it1) }
                        }
                    })
            }
        }
    }
}