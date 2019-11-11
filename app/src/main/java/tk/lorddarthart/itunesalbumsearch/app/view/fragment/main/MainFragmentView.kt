package tk.lorddarthart.itunesalbumsearch.app.view.fragment.main

import android.view.View
import com.arellomobile.mvp.MvpView

/**
 * Created by LordDarthArt on 10.11.2019.
 */
interface MainFragmentView: MvpView, View.OnClickListener {
    fun triggerSearch(searchString: String)

    fun triggerError(errorString: String)

    fun triggerRecycler()
}