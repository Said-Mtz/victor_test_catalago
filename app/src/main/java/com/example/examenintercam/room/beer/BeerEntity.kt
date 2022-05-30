package com.example.examenintercam.room.beer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_beer")
data class BeerEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 1,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "tagline")
    val tagline: String,

    @ColumnInfo(name = "image_url")
    val image_url: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "favorite")
    val isFavorite: Boolean

/*val abv: Double,
val ibu: Double,
val target_og: Double,
val target_fg: Int,
val first_brewed: String,
val brewers_tips: String,
val food_pairing: List<String>,
val ingredients: Ingredients*/
)
