package com.railian.mobile.nolibschallange.data.pojo

data class SearchResult(
    val additionalArtists: List<AdditionalArtist> = listOf(),
    val adfunded: Boolean = false,
    val bundleOnly: Boolean = false,
    val cover: Cover = Cover(),
    val duration: Int = 0,
    val extendedMetaData: ExtendedMetaData = ExtendedMetaData(),
    val genres: List<String> = listOf(),
    val id: Int = 0,
    val idBag: IdBag = IdBag(),
    val mainArtist: MainArtist = MainArtist(),
    val publishingDate: String = "",
    val release: Release = Release(),
    val statistics: Statistics = Statistics(),
    val streamable: Boolean = false,
    val title: String = "",
    val trackNumber: Int = 0,
    val type: String = "",
    val volumeNumber: Int = 0
) {
    data class AdditionalArtist(
        val id: Int = 0,
        val name: String = "",
        val role: String = ""
    )

    data class Cover(
        val large: String = "",
        val medium: String = "",
        val small: String = "",
        val tiny: String = ""
    )

    data class ExtendedMetaData(
        val genresHierarchy: List<String> = listOf(),
        val gracenoteRythmApiGenreIds: String = "",
        val languages: String = "",
        val moods: List<String> = listOf(),
        val originalSongId: String = "",
        val originalTitle: String = "",
        val releaseYear: String = "",
        val tempos: List<String> = listOf()
    )

    data class IdBag(
        val roviTrackId: String = ""
    )

    data class MainArtist(
        val id: Int = 0,
        val name: String = "",
        val role: String = ""
    )

    data class Release(
        val id: Int = 0,
        val title: String = ""
    )

    data class Statistics(
        val estimatedRecentCount: Int = 0,
        val estimatedTotalCount: Int = 0,
        val popularity: Int = 0
    )
}