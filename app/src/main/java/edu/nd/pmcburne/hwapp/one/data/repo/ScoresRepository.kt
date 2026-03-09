package edu.nd.pmcburne.hwapp.one.data.repo

import edu.nd.pmcburne.hwapp.one.data.remote.NcaaApiService
import edu.nd.pmcburne.hwapp.one.domain.Gender
import edu.nd.pmcburne.hwapp.one.domain.Game
import edu.nd.pmcburne.hwapp.one.domain.GameMapper
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.content.Context
import edu.nd.pmcburne.hwapp.one.data.local.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import edu.nd.pmcburne.hwapp.one.data.util.NetworkMonitor

class ScoresRepository(context: Context) {

    private val dao = AppDatabase.get(context).gameDao()
    private val network = NetworkMonitor(context)

    private val api: NcaaApiService = Retrofit.Builder()
        .baseUrl("https://ncaa-api.henrygd.me/")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(NcaaApiService::class.java)

    fun observeGames(dateKey: String, gender: Gender): Flow<List<Game>> {
        return dao.observeGames(dateKey, gender.name).map { list ->
            list.map { GameMapper.entityToGame(it) }
        }
    }

    /**
     * returns true if network refresh happened, false if skipped (offline)
     */
    suspend fun refreshToDb(dateKey: String, gender: Gender): Boolean {
        if (!network.isOnline()) {
            return false
        }

        val (yyyy, mm, dd) = dateKey.split("-")
        val dto = api.getScoreboard(gender.urlSegment, yyyy, mm, dd)

        val entities = dto.games.map { wrapper ->
            GameMapper.dtoToEntity(wrapper.game, dateKey, gender)
        }

        dao.clearDate(dateKey, gender.name)
        dao.upsertAll(entities)

        return true
    }
}