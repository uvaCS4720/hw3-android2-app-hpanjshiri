package edu.nd.pmcburne.hwapp.one.data.repo

import edu.nd.pmcburne.hwapp.one.data.remote.NcaaApiService
import edu.nd.pmcburne.hwapp.one.domain.Gender
import edu.nd.pmcburne.hwapp.one.domain.Game
import edu.nd.pmcburne.hwapp.one.domain.GameMapper
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ScoresRepository {

    private val api: NcaaApiService = Retrofit.Builder()
        .baseUrl("https://ncaa-api.henrygd.me/")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(NcaaApiService::class.java)

    suspend fun fetchGames(dateKey: String, gender: Gender): List<Game> {
        val parts = dateKey.split("-")
        val yyyy = parts[0]
        val mm = parts[1]
        val dd = parts[2]

        val dto = api.getScoreboard(gender.urlSegment, yyyy, mm, dd)
        return dto.games.map { wrapper -> GameMapper.dtoToGame(wrapper.game) }
    }
}