package com.binar.pra_project.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * From favorite_table where username = :username")
    suspend fun getFavoriteCart(username: String): List<FavoriteBarang>

    @Query("SELECT EXISTS(SELECT * FROM favorite_table WHERE id_barang = :id_barang AND username = :username)")
    suspend fun isFavorit(id_barang: Int, username: String): Boolean

    @Insert
    suspend fun addFavorite(favoriteEntity: FavoriteBarang)

    @Query("DELETE FROM favorite_table WHERE id_barang = :id_barang AND username = :username")
    suspend fun deleteFavorite(id_barang: Int, username: String)
}