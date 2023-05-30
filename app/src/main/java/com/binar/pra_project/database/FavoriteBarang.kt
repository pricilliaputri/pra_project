package com.binar.pra_project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
class FavoriteBarang (
    @PrimaryKey
    @ColumnInfo(name = "id_barang")
    val id_barang : Int,
    @ColumnInfo(name = "photo_barang")
    val photo : String,
    @ColumnInfo(name = "title_barang")
    val title : String,
    @ColumnInfo(name = "username")
    val username : String,
)