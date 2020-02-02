package com.railian.mobile.nolibschallange.data.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class SearchResult(
    val additionalArtists: List<Artist> = listOf(),
    val cover: Cover = Cover(),
    val id: Int = 0,
    val mainArtist: Artist = Artist(),
    val title: String = "",
    val type: String = "",
    val release: Release
) : Parcelable {

    companion object {
        @JvmStatic
        @Throws(JSONException::class)
        fun unBox(json: String): SearchResult {

            val jsonObject = JSONObject(json)
            val additionalArtists = mutableListOf<Artist>()
            val additionalArtistsArray = jsonObject.optJSONArray("additionalArtists")

            for (i in 0 until additionalArtistsArray.length()) {
                additionalArtists.add(Artist.unBox(additionalArtistsArray[i] as JSONObject))
            }

            return SearchResult(
                additionalArtists,
                Cover.unBox(jsonObject.optJSONObject("cover")),
                jsonObject.optInt("id"),
                Artist.unBox(jsonObject.optJSONObject("mainArtist")),
                jsonObject.optString("title"),
                jsonObject.optString("type"),
                Release.unBox(jsonObject.optJSONObject("release"))
            )
        }
    }

    @Parcelize
    data class Cover(
        val large: String = "",
        val medium: String = "",
        val small: String = "",
        val tiny: String = ""
    ) : Parcelable {
        companion object {
            @JvmStatic
            @Throws(JSONException::class)
            fun unBox(jsonObject: JSONObject): Cover {
                return Cover(
                    jsonObject.optString("large"),
                    jsonObject.optString("medium"),
                    jsonObject.optString("small"),
                    jsonObject.optString("tiny")
                )
            }
        }
    }

    @Parcelize
    data class Artist(
        val id: Int = 0,
        val name: String = "",
        val role: String = ""
    ) : Parcelable {
        companion object {
            @JvmStatic
            @Throws(JSONException::class)
            fun unBox(jsonObject: JSONObject): Artist {
                return Artist(
                    jsonObject.optInt("id"),
                    jsonObject.optString("name"),
                    jsonObject.optString("role")
                )
            }
        }
    }

    @Parcelize
    data class Release(
        val id: Int = 0,
        val title: String = ""
    ) : Parcelable {
        companion object {
            @JvmStatic
            @Throws(JSONException::class)
            fun unBox(jsonObject: JSONObject): Release {
                return Release(
                    jsonObject.optInt("id"),
                    jsonObject.optString("title")
                )
            }
        }
    }
}