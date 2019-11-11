package tk.lorddarthart.itunesalbumsearch.app.view.fragment.about_album.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.itunesalbumsearch.R

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val trackNumber = itemView.findViewById<TextView>(R.id.track_number)
    val trackName = itemView.findViewById<TextView>(R.id.track_name)
    val trackDuration = itemView.findViewById<TextView>(R.id.track_duration)
}