package com.example.examenintercam.data

import android.util.Base64
import androidx.lifecycle.Observer
import com.example.examenintercam.core.api.ApiBeerService
import com.example.examenintercam.core.api.RetrofitService
import com.example.examenintercam.core.model.BeerRequestModel
import com.example.examenintercam.core.model.BeerRequestModelItem
import com.example.examenintercam.room.AppDataBase
import com.example.examenintercam.room.beer.BeerEntity
import com.example.examenintercam.room.user.UserEntity
import com.example.examenintercam.ui.StatusRequest
import com.example.examenintercam.ui.fragments.favorites.FavoritesModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiBeerService
    private var lastPage = 0
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {
        lateinit var dataBase: AppDataBase
    }

    init {
        val retrofit = RetrofitService.instance()
        apiService = retrofit.create(ApiBeerService::class.java)
    }

    private fun encript(dataToEncript: String) =
        Base64.encodeToString(dataToEncript.toByteArray(), Base64.DEFAULT)


    private fun decrypt(dataEncripted: String) =
        String(Base64.decode(dataEncripted, Base64.DEFAULT))

    fun executeBeerData(
        oldList: Pair<BeerRequestModel, List<FavoritesModel>>?,
        observer: Observer<Pair<StatusRequest, Pair<BeerRequestModel, List<FavoritesModel>>?>>
    ) {
        lastPage += 1
        coroutineScope.launch {
            apiService.getBeer(lastPage.toString()).enqueue(object : Callback<BeerRequestModel> {
                override fun onResponse(
                    call: Call<BeerRequestModel>,
                    response: Response<BeerRequestModel>
                ) {
                    val body = response.body()

                    if (body != null) {
                        if (body.isEmpty()) {
                            observer.onChanged(Pair(StatusRequest.NONE, oldList))
                            lastPage -= 1
                        } else {
                            body.forEach {
                                oldList?.first?.add(it)
                            }
                            executeGetFavorite {
                                val newPair = Pair(oldList?.first ?: BeerRequestModel(), it)

                                observer.onChanged(Pair(StatusRequest.SUCCESS, newPair))
                            }
                        }
                    } else {
                        observer.onChanged(Pair(StatusRequest.FAILURE, oldList))
                    }
                }

                override fun onFailure(call: Call<BeerRequestModel>, t: Throwable) {
                    lastPage -= 1
                    observer.onChanged(Pair(StatusRequest.FAILURE, oldList))
                }
            })
        }
    }

    fun executeGetFavorite(
        observer: Observer<Pair<StatusRequest, List<FavoritesModel>?>>? = null,
        callback: (List<FavoritesModel>) -> Unit = {}
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val favoriteSaved = dataBase.beerDao.getAllBeer().map {
                    FavoritesModel(
                        id = it.id,
                        nameBeer = it.name,
                        yeast = it.tagline,
                        img = it.image_url,
                        rateFromLocal = it.rating
                    )
                }
                observer?.onChanged(
                    Pair(StatusRequest.SUCCESS, favoriteSaved)
                )
                callback(favoriteSaved)

            } catch (exception: Exception) {
                observer?.onChanged(Pair(StatusRequest.FAILURE, null))
            }
        }
    }

    fun executeSetFavorite(
        beerItem: FavoritesModel,
        rate: Double = 0.0,
        observer: Observer<Pair<StatusRequest, Boolean?>>,
        callback: (List<FavoritesModel>) -> Unit
    ) {
        coroutineScope.launch {
            try {
                dataBase.beerDao.saveFavorite(
                    BeerEntity(
                        id = beerItem.id,
                        name = beerItem.nameBeer,
                        image_url = beerItem.img,
                        tagline = beerItem.yeast,
                        rating = rate,
                        isFavorite = true
                    )
                )
                executeGetFavorite() {
                    observer.onChanged(
                        Pair(StatusRequest.SUCCESS, true)
                    )
                    callback(it)
                }

            } catch (exception: Exception) {
                observer.onChanged(Pair(StatusRequest.FAILURE, null))
            }
        }
    }

    fun executeSetFavorite(
        beerItem: BeerRequestModelItem,
        observer: Observer<Pair<StatusRequest, Boolean?>>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                dataBase.beerDao.saveFavorite(
                    BeerEntity(
                        id = beerItem.id,
                        name = beerItem.name,
                        image_url = beerItem.image_url ?: "",
                        tagline = beerItem.tagline,
                        rating = 0.0,
                        isFavorite = true
                    )
                )
                observer.onChanged(
                    Pair(StatusRequest.SUCCESS, true)
                )

            } catch (exception: Exception) {
                observer.onChanged(Pair(StatusRequest.FAILURE, null))
            }
        }
    }

    fun executeSaveUser(listUser: List<UserEntity>, execute: () -> Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            listUser.forEach {
                val passwordEncrypted = encript(it.password)
                dataBase.userDao.insert(
                    UserEntity(
                        email = it.email, password = passwordEncrypted
                    )
                )
            }
            execute()
        }
    }

    fun executeGetUser(
        user: String,
        password: String,
        observer: Observer<Pair<StatusRequest, Boolean?>>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val userExist = dataBase.userDao.isUserExist(user, encript(password))
                observer.onChanged(
                    Pair(
                        if (userExist) StatusRequest.SUCCESS else StatusRequest.FAILURE,
                        userExist
                    )
                )

            } catch (exception: Exception) {
                observer.onChanged(Pair(StatusRequest.FAILURE, null))
            }
        }
    }

}