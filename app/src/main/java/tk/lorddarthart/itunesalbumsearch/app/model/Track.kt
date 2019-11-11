package tk.lorddarthart.itunesalbumsearch.app.model

import com.google.gson.annotations.Expose

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class Track {
    @Expose
    var wrapperType: String? = null

    @Expose
    var kind: String? = null

    @Expose
    var artistId: Long? = null

    @Expose
    var collectionId: Long? = null

    @Expose
    var trackId: Long? = null

    @Expose
    var artistName: String? = null

    @Expose
    var collectionName: String? = null

    @Expose
    var trackName: String? = null

    @Expose
    var collectionCensoredName: String? = null

    @Expose
    var trackCensoredName: String? = null

    @Expose
    var artistViewUrl: String? = null

    @Expose
    var collectionViewUrl: String? = null

    @Expose
    var trackViewUrl: String? = null

    @Expose
    var previewUrl: String? = null

    @Expose
    var artworkUrl30: String? = null

    @Expose
    var artworkUrl60: String? = null

    @Expose
    var artworkUrl100: String? = null

    @Expose
    var collectionPrice: Double? = null

    @Expose
    var trackPrice: Double? = null

    @Expose
    var releaseDate: String? = null

    @Expose
    var collectionExplicitness: String? = null

    @Expose
    var trackExplicitness: String? = null

    @Expose
    var discCount: Int? = null

    @Expose
    var discNumber: Int? = null

    @Expose
    var trackCount: Int? = null

    @Expose
    var trackNumber: Int? = null

    @Expose
    var trackTimeMillis: Int? = null

    @Expose
    var country: String? = null

    @Expose
    var currency: String? = null

    @Expose
    var primaryGenreName: String? = null

    @Expose
    var isStreamable: Boolean? = null
}