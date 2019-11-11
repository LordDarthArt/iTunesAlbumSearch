package tk.lorddarthart.itunesalbumsearch.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ITunesAlbumResponse {
    @Expose
    var resultsCount: Int? = null

    @Expose
    var results: List<Album>? = null
}