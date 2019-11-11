package tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.app.App
import tk.lorddarthart.itunesalbumsearch.app.model.Track
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class TrackAdapter(
    private val trackList: List<Track>
): RecyclerView.Adapter<TrackViewHolder>() {
    var singleTrackView: View? = null
    var singleTrackViewHolder: TrackViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        singleTrackView = LayoutInflater.from(App.instance).inflate(
            R.layout.item_single_track,
            parent,
            false
        )

        singleTrackViewHolder = TrackViewHolder(singleTrackView!!)
        return singleTrackViewHolder!!
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = trackList[position]
        with (holder) {
            trackNumber.text = track.trackNumber.toString()
            trackName.text = track.trackCensoredName.toString()
            track.trackTimeMillis?.toLong()?.let { millis ->
                trackDuration.text = SimpleDateFormat("mm:ss").format(Date(millis)).toString()
            }
        }
    }
}