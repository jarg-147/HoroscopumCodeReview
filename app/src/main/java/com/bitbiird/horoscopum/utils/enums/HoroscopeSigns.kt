package com.bitbiird.horoscopum.utils.enums

import com.bitbiird.horoscopum.R
import java.io.Serializable

enum class HoroscopeSigns(val signName: Int, val signIcon: Int, val signDate: Int) : Serializable {
    Capricorn(R.string.capricorn, R.drawable.ic_capricorn, R.string.capricorn_date),
    Aquarium(R.string.aquarium, R.drawable.ic_aquarium, R.string.aquarium_date),
    Pisces(R.string.pisces, R.drawable.ic_pisces, R.string.pisces_date),
    Aries(R.string.aries, R.drawable.ic_aries, R.string.aries_date),
    Taurus(R.string.taurus, R.drawable.ic_taurus, R.string.taurus_date),
    Gemini(R.string.gemini, R.drawable.ic_gemini, R.string.gemini_date),
    Cancer(R.string.cancer, R.drawable.ic_cancer, R.string.cancer_date),
    Leo(R.string.leo, R.drawable.ic_leo, R.string.leo_date),
    Virgo(R.string.virgo, R.drawable.ic_virgo, R.string.virgo_date),
    Libra(R.string.libra, R.drawable.ic_libra, R.string.libra_date),
    Scorpio(R.string.scorpio, R.drawable.ic_scorpio, R.string.scorpio_date),
    Sagittarius(R.string.sagittarius, R.drawable.ic_sagittarius, R.string.sagittarius_date);
}
