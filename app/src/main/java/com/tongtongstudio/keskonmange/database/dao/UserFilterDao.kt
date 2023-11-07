package com.tongtongstudio.keskonmange.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tongtongstudio.keskonmange.database.UserFilters
import kotlinx.coroutines.flow.Flow

@Dao
interface UserFilterDao {
    @Insert
    suspend fun insertFilter(filter: UserFilters)

    @Query("DELETE FROM user_filters")
    suspend fun deleteAllFilters()

    @Query("DELETE FROM user_filters WHERE id = :id")
    suspend fun deleteUserFilter(id: Long)

    @Query("SELECT * FROM user_filters ORDER BY random()")
    fun getAllFilters(): Flow<List<UserFilters>>

    @Query("SELECT * FROM user_filters WHERE filter_name = :name ")
    fun getUserFilterByName(name : String): Flow<UserFilters>
}