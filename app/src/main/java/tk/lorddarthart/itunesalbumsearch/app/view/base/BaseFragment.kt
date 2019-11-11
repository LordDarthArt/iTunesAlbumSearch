package tk.lorddarthart.itunesalbumsearch.app.view.base

import android.content.Context
import tk.lorddarthart.itunesalbumsearch.app.view.activity.MainActivity
import tk.lorddarthart.itunesalbumsearch.util.moxy.MvpFragment

/**
 * Base Fragment class that is parent to other fragments of this application
 */
open class BaseFragment : MvpFragment() {
    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }
}