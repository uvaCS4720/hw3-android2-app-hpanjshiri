package edu.nd.pmcburne.hwapp.one.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("""
        SELECT * FROM games
        WHERE dateKey = :dateKey AND gender = :gender
        ORDER BY homeTeam ASC
    """)
    fun observeGames(dateKey: String, gender: String): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(list: List<GameEntity>)

    @Query("DELETE FROM games WHERE dateKey = :dateKey AND gender = :gender")
    suspend fun clearDate(dateKey: String, gender: String)
}