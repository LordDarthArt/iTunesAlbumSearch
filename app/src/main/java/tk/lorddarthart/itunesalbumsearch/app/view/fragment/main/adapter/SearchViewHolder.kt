package tk.lorddarthart.itunesalbumsearch.app.view.fragment.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.util.helper.OnItemTouchListener

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val albumArt: ImageView = itemView.findViewById(R.id.album_art)
    val albumTitle: TextView = itemView.findViewById(R.id.album_title)
    val albumArtist: TextView = itemView.findViewById(R.id.album_artist)
    val albumCost: TextView = itemView.findViewById(R.id.album_cost)
    val albumCopyright: TextView = itemView.findViewById(R.id.album_copyright)

    constructor(itemView: View, onClick: OnItemTouchListener) : this(itemView) {
        itemView.setOnClickListener {
            onClick.onCardViewTap(itemView, layoutPosition)
        }
    }
}