package com.example.dailyquiz.domain.model

enum class QuizCategory(val id: Int, val displayName: String) {
    GENERAL_KNOWLEDGE(9, "General Knowledge"),
    BOOKS(10, "Books"),
    FILM(11, "Film"),
    MUSIC(12, "Music"),
    MUSICALS_THEATRE(13, "Musicals & Theatres"),
    TELEVISION(14, "Television"),
    VIDEO_GAMES(15, "Video Games"),
    BOARD_GAMES(16, "Board Games"),
    SCIENCE_NATURE(17, "Science & Nature"),
    COMPUTERS(18, "Science & Computers"),
    MATH(19, "Science & Math"),
    MYTHOLOGY(20, "Mythology"),
    SPORTS(21, "Sports"),
    GEOGRAPHY(22, "Geography"),
    HISTORY(23, "History"),
    POLITICS(24, "Politics"),
    ART(25, "Art"),
    CELEBRITIES(26, "Celebrities"),
    ANIMALS(27, "Animals"),
    VEHICLES(28, "Vehicles"),
    COMICS(29, "Comics"),
    GADGETS(30, "Science & Gadgets"),
    ANIME(31, "Anime & Manga"),
    CARTOONS(32, "Cartoons & Animations");

    override fun toString(): String = displayName

    companion object {
        fun fromId(id: Int): String {
            return values().find { it.id == id }?.displayName
                ?: "General Knowledge"
        }
    }
}