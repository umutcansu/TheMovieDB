package com.example.mymovie.model

data class MovieDetailModel(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var belongs_to_collection: Any = Any(),
    var budget: Int = 0,
    var genres: List<Genre> = listOf(),
    var homepage: String = "",
    var id: Int = 0,
    var imdb_id: String = "",
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var production_companies: List<ProductionCompany> = listOf(),
    var production_countries: List<ProductionCountry> = listOf(),
    var release_date: String = "",
    var revenue: Int = 0,
    var runtime: Int = 0,
    var spoken_languages: List<SpokenLanguage> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0
) {
    data class Genre(
        var id: Int = 0,
        var name: String = ""
    )

    data class ProductionCompany(
        var id: Int = 0,
        var logo_path: String = "",
        var name: String = "",
        var origin_country: String = ""
    )

    data class ProductionCountry(
        var iso_3166_1: String = "",
        var name: String = ""
    )

    data class SpokenLanguage(
        var iso_639_1: String = "",
        var name: String = ""
    )
}