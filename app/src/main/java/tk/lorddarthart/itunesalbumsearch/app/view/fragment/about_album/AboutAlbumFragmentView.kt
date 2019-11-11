package tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album

import com.arellomobile.mvp.MvpView
import tk.lorddarthart.itunesalbumsearch.app.model.Album

/**
 * Created by LordDarthArt on 10.11.2019.
 */
interface AboutAlbumFragmentView: MvpView {
    fun triggerRecycler()

    fun triggerError(error: String)

    fun triggerFragment(targetAlbum: Album?)
}