package tk.lorddarthart.itunesalbumsearch.app.model

import com.google.gson.annotations.Expose

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class ITunesTrackResponse {
    @Expose
    var resultsCount: Int? = null

    @Expose
    var results: List<Track>? = null
}