package tk.lorddarthart.itunesalbumsearch.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Album model with info we are receiving from server
class Album {
    @Expose
    var wrapperType: String? = null

    @Expose
    var collectionType: String? = null

    @Expose
    var artistId: Long? = null

    @Expose
    var collectionId: Long? = null

    @Expose
    var amgArtistId: Long? = null

    @Expose
    var artistName: String? = null

    @Expose
    var collectionName: String? = null

    @Expose
    var collectionCensoredName: String? = null

    @Expose
    var artistViewUrl: String? = null

    @Expose
    var collectionViewUrl: String? = null

    @Expose
    var artworkUrl60: String? = null

    @Expose
    var artworkUrl100: String? = null

    @Expose
    var collectionPrice: Double? = null

    @Expose
    var collectionExplicitness: String? = null

    @Expose
    var trackCount: Int? = null

    @Expose
    var copyright: String? = null

    @Expose
    var country: String? = null

    @Expose
    var currency: String? = null

    @Expose
    var releaseDate: String? = null

    @Expose
    var primaryGenreName: String? = null
}