package tk.lorddarthart.itunesalbumsearch.app.view.fragment.main.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tk.lorddarthart.itunesalbumsearch.R
import tk.lorddarthart.itunesalbumsearch.app.App
import tk.lorddarthart.itunesalbumsearch.app.model.Album
import tk.lorddarthart.itunesalbumsearch.util.helper.OnItemTouchListener
import tk.lorddarthart.itunesalbumsearch.util.helper.UtilFunctions
import tk.lorddarthart.itunesalbumsearch.util.helper.UtilFunctions.animatedImgLoad
import tk.lorddarthart.itunesalbumsearch.util.helper.UtilFunctions.roundOffDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class SearchAdapter(
    private val albumList: List<Album?>?,
    private val onClick: OnItemTouchListener
) : RecyclerView.Adapter<SearchViewHolder>() {
    private lateinit var singleUserView: View
    private var singleAlbumViewHolder: SearchViewHolder? = null

    override fun getItemCount(): Int {
        albumList?.size?.let {
            return it
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val album = albumList?.get(position)
        with(holder) {
            albumTitle.text = album?.collectionCensoredName
            albumArtist.text = "${App.instance.getString(R.string.artist)} ${album?.artistName}"
            albumCost.text = "${App.instance.getString(R.string.price)} ${album?.collectionPrice?.let {
                "\$" + roundOffDecimal(
                    it
                )
            } ?: App.instance.getString(R.string.free_price)}"
            albumCopyright.text = album?.copyright
        }
        try {
            animatedImgLoad(album?.artworkUrl100, holder.albumArt)
        } catch (e: Exception) {
            Toast
                .makeText(
                    App.instance,
                    e.message,
                    Toast.LENGTH_LONG
                ).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        singleUserView = LayoutInflater.from(App.instance).inflate(
            R.layout.item_single_album,
            parent,
            false
        )

        singleAlbumViewHolder = SearchViewHolder(singleUserView, onClick)
        return singleAlbumViewHolder as SearchViewHolder
    }
}