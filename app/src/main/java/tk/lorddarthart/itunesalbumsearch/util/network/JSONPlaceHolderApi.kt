package tk.lorddarthart.itunesalbumsearch.util.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.itunesalbumsearch.app.model.ITunesAlbumResponse
import tk.lorddarthart.itunesalbumsearch.app.model.ITunesTrackResponse
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.LOOKUP_URL
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.SEARCH_PARAMETER_ENTITY
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.SEARCH_PARAMETER_ATTRIBUTE
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.SEARCH_PARAMETER_ID
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.SEARCH_PARAMETER_TERM
import tk.lorddarthart.itunesalbumsearch.util.constants.UrlConstants.SEARCH_URL

interface JSONPlaceHolderApi {
    @GET(SEARCH_URL)
    fun getAlbum(
        @Query(value = SEARCH_PARAMETER_TERM, encoded = true) term: String,
        @Query(value = SEARCH_PARAMETER_ENTITY, encoded = true) entity: String,
        @Query(value = SEARCH_PARAMETER_ATTRIBUTE, encoded = true) attribute: String
    ): Call<ITunesAlbumResponse>


    @GET(LOOKUP_URL)
    fun getTracks(
        @Query(value = SEARCH_PARAMETER_ID, encoded = true) term: String,
        @Query(value = SEARCH_PARAMETER_ENTITY, encoded = true) entity: String
    ): Call<ITunesTrackResponse>
}